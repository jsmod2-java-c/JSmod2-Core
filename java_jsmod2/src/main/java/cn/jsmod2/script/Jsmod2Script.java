package cn.jsmod2.script;

import cn.jsmod2.Register;
import cn.jsmod2.script.function.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jsmod2服务器脚本的解析器
 * script进入脚本解析页面
 */
public class Jsmod2Script {

    static {
        script = new Jsmod2Script();
        getScript().functions.put("echo",new EchoFunction());
        getScript().functions.put("typeof",new TypeOfFunction());
        getScript().functions.put("import",new ImportFunction());
        getScript().functions.put("register",new RegisterNativeFunction());
    }

    private static Jsmod2Script script;

    private Map<String,String> matches = Register.getInstance().getScriptPettern();

    private Map<String, Function> functions = new HashMap<>();

    //全局变量
    private Map<String,Var> vars = new HashMap<>();

    public Map<String, Function> getFunctions() {
        return functions;
    }

    public void importFile(String file) throws IOException {
        //utf-8
        //文件头部指定字符集
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String coding = reader.readLine();
        List<String> codes = FileUtils.readLines(new File(file),coding);
        for(int i = 1;i<codes.size();i++){
            StringBuilder getFunc = new StringBuilder(codes.get(i));
            if(codes.get(i).matches(Register.getInstance().getScriptPettern().get("startfunc"))){
                while (!getFunc.toString().endsWith(":end")){
                    getFunc.append(codes.get(i).replaceAll(" ",""));
                    i++;
                }
            }
            parse(getFunc.toString());
        }
    }

    //a=0
    private Object parseVar(String cmd,Map<String,Var> vars){
        if(!cmd.matches(matches.get("var"))){
            return "";
        }
        String[] key_value = cmd.split("=");
        return parseVar(key_value[0],key_value[1],vars);
    }

    private Var parseVar(String key,String value,Map<String,Var> vars){
        if(vars.get(key)!=null){
            Var var = vars.get(key);
            var.setValue(value);
            return var;
        }else{
            Var var = new Var(value);
            vars.put(key,var);
            return var;
        }
    }

    //list
    private String listVar(String cmd){
        if(cmd.matches(matches.get("list"))) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Var> var : vars.entrySet()) {
                builder.append(var.getKey());
                builder.append("-");
                builder.append(var.getValue());
                builder.append("\n");
            }
            return builder.toString();
        }
        return "";
    }

    public static boolean matchPettern(String command){
        Map<String,String> patterns = Register.getInstance().getScriptPettern();
        for(String pattern:patterns.values()){
            if(command.matches(pattern)){
                return true;
            }
        }
        return false;
    }
    //unset a=0

    private String unset(String command,Map<String,Var> vars){
        if(command.matches(matches.get("unset"))){
            String[] unsets = command.split(" ");
            String name;
            if(unsets[1].contains("=")){
                name = unsets[1].substring(0,unsets[1].indexOf("="));
            }else{
                name = unsets[1];
            }
            Var var = vars.get(name);
            if(var == null){
                return "no such var";
            }
            var.unset();
            return unsets[1];
        }
        return command;
    }

    public Object executeFunction(String func){
        if(!func.matches(matches.get("func"))){
            return "";
        }
        String[] strs = func.split("=");
        String funcName= func;
        if(strs.length==2){
            funcName = strs[1];
        }
        String[] args = funcName.substring(funcName.indexOf("(")+1,funcName.lastIndexOf(")")).split(",");
        args = setThat(args);
        for(int i = 0;i<args.length;i++){
            args[i] = executeFunction(args[i])==null?"NULL":args[i].toString();
        }
        funcName = funcName.replace("f::","").replaceAll("\\(([\\s\\S]+|[\\s\\S]*)\\)","");
        Function function = functions.get(funcName);
        if(function==null){
            return "no such function!";
        }

        if(function instanceof NativeFunction){
            Object object = ((NativeFunction) function).execute(args);
            return object==null?"NULL":object;
        }
        //普通函数还没开始处理
        //形式参数作用域在方法，方法执行完则销毁
        Map<String,Var> vars_func = new HashMap<>();

        vars_func.putAll(vars);

        //形式参数
        String[] alls = function.getArgs();
        //形式参数
        for(int i =0;i<args.length;i++){
            vars_func.put(alls[i],new Var(args[i]));
        }

        String code = function.getCode();

        String[] codes =code.split(";");

        for(int i = 0;i<codes.length-1;i++) {
            Jsmod2Script.parse(codes[i],vars_func);
        }

        return Jsmod2Script.parse(codes[codes.length-1]);
    }

    private String getFunctionVarName(String func){
        if(func.matches(matches.get("func"))){
            String[] strs = func.split("=");
            if(strs.length==2){
                return strs[0];
            }
        }
        return null;
    }
    //定义函数是
    // func name(a,b);start:
    //  语句
    //  语句
    // :end
    //
    private String defineFunction(String func){
        if(!func.matches(matches.get("dfunc"))){
            return func;
        }
        String[] alls = func.split(" ");
        String[] name_start = alls[1].split(";");
        String name = name_start[0].replaceAll("\\([\\s\\S]+\\)","");
        Function function = new Function("",func.replaceAll(matches.get("startfunc"),"").replace(":end","")) {};

        String[] args = name_start[0].substring(name_start[0].indexOf("(")+1,name_start[0].indexOf(")")).split(",");
        function.setArgs(args);
        functions.put(name.replaceAll("\\(([\\s\\S]+|)\\)",""),function);

        return "create successfully";
    }

    public static String parse(String command,Map<String,Var> vars){
        StringBuilder builder = new StringBuilder();
        //执行函数可以返回值
        //a=echo()
        getScript().defineFunction(command);
        Object obj = script.parseVar(script.unset(command,vars),vars);
        builder.append(obj);
        builder.append(script.listVar(command));
        Object o = script.executeFunction(command);
        String varName = script.getFunctionVarName(command);
        if(varName!=null){
            if(obj instanceof Var){
                ((Var) obj).unset();
            }
            builder.append(script.parseVar(varName,o==null?"NULL":o.toString(),vars).getValue());
        }

        return builder.toString();
    }

    public static String parse(String command){
        return parse(command,script.vars);
    }

    public static String[] setThat(String[] args){
        String[] dArgs = new String[args.length];
        int i = 0;
        //关于变量
        for(String arg:args){
            String lo = arg;
            for(Map.Entry<String,Var> var:getScript().vars.entrySet()){
                lo = lo.replace("${"+var.getKey()+"}",var.getValue().getValue());
            }
            dArgs[i] = lo;
            i++;
        }
        return dArgs;
    }



    public static Jsmod2Script getScript() {
        return script;
    }

    public Map<String, Var> getVars() {
        return vars;
    }
}
