package cn.jsmod2.script;

import cn.jsmod2.script.function.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jsmod2服务器脚本的解析器
 * script进入脚本解析页面
 */
public class EmeraldScript_JavaParser {

    static {
        script = new EmeraldScript_JavaParser();
        getScript().functions.put("echo",new EchoFunction());
        getScript().functions.put("typeof",new TypeOfFunction());
        getScript().functions.put("import",new ImportFunction());
        getScript().functions.put("register",new RegisterNativeFunction());
        getScript().functions.put("if",new IfFunction());
    }

    private static EmeraldScript_JavaParser script;

    private Map<String, Function> functions = new HashMap<>();

    //全局变量
    private Map<String,Var> vars = new HashMap<>();

    public Map<String, Function> getFunctions() {
        return functions;
    }

    /**
     * 导入文件(实际调用函数)
     * @param file
     * @throws IOException
     */
    public void importFile(String file) throws IOException {
        //utf-8
        //文件头部指定字符集
        int comment = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String coding = reader.readLine();
        List<String> codes = FileUtils.readLines(new File(file),coding);
        for(int i = 1;i<codes.size();i++){
            if(codes.get(i).startsWith("\"\"\"")){
                comment++;
                continue;
            }
            if(comment%2==1&&comment!=0){
                continue;
            }
            if(codes.get(i).matches("#[\\s\\S]+")){
                continue;
            }
            StringBuilder getFunc = new StringBuilder(codes.get(i));
            if(codes.get(i).matches(Memory.matches.get("startfunc"))){
                while (!getFunc.toString().endsWith(":end")){
                    getFunc.append(codes.get(i).replaceAll(" ","").replaceAll("#[\\s\\S]+",""));
                    i++;
                }
                i--;
            }
            if(codes.get(i).matches(Memory.matches.get("start"))){
                while (!getFunc.toString().endsWith("}")){
                    getFunc.append(codes.get(i));
                    i++;
                }
                i--;
            }
            parse(getFunc.toString());
        }
    }

    /*
     * if[表达式]{
     *
     * }elif[表达式]{
     *
     * }else{
     *
     * }
     * while[表达式]{
     * }
     *
     * do{
     * }while[表达式];
     *
     * for[i in range(10)]{
     * }
     *
     *
     */
    private Object parseIf(String cmd){
        if(!cmd.matches(Memory.matches.get("if"))){
            return cmd;
        }



        return null;
    }

    /**
     * 计算运算表达式
     * let a = (1+1+2+3/4)*2
     * 先算乘除 后算加减
     *
     * @param expression
     * @return
     */
    private String performCalculations(String expression){
        if(!expression.matches(Memory.matches.get("pc"))){
            return expression;
        }

        return "";
    }

    private String performBoolean(String expression){
        return "";
    }




    /**
     * 解析全局变量
     * @param cmd
     * @param vars
     * @return
     */
    //a=0
    private Object parseVar(String cmd,Map<String,Var> vars){
        if(cmd.matches(Memory.matches.get("pc"))){
            return cmd;
        }
        if(cmd.matches(Memory.matches.get("func"))){
            return cmd;
        }
        if(!cmd.matches(Memory.matches.get("var"))){
            return cmd;
        }
        String[] key_value = cmd.split("=");
        Var var = parseVar(key_value[0],setThat(key_value[1])[0],vars,setThat(cmd)[0]);
        return var+"TYPE:"+var.getType();
    }

    /**
     * 解析局部变量
     * @param key
     * @param value
     * @param vars
     * @return
     */
    private Var parseVar(String key,String value,Map<String,Var> vars,String cmd){
        if(vars.get(key)!=null){
            Var var = vars.get(key);
            var.setValue(value);
            return var;
        }else{
            Var var = Var.compile(cmd);
            vars.put(key,var);
            return var;
        }
    }

    /**
     * 列出内存的变量
     * @param cmd
     * @return
     */
    //list
    private String listVar(String cmd){
        if(cmd.matches(Memory.matches.get("list"))) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Var> var : vars.entrySet()) {
                builder.append(var.getKey());
                builder.append("-");
                builder.append(var.getValue());
                builder.append("\n");
            }
            return builder.toString();
        }
        return cmd;
    }

    /**
     * 检查是否符合语法
     * @param command
     * @return
     */
    public static boolean matchPattern(String command){
        Map<String,String> patterns = Memory.matches;
        for(String pattern:patterns.values()){
            if(command.matches(pattern)){
                return true;
            }
        }
        return false;
    }
    //unset a=0

    /**
     *执行重置指令
     * @param command
     * @param vars
     * @return
     */
    private String unset(String command,Map<String,Var> vars){
        if(command.matches(Memory.matches.get("unset"))){
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

    /**
     * 执行一个函数
     * @param func
     * @return
     */
    public Object executeFunction(String func){
        if(!func.matches(Memory.matches.get("func"))){
            return func;
        }
        String[] strs = func.split("=");
        String funcName= func;
        if(strs.length==2){
            funcName = strs[1];
        }
        String last = ")";
        if(funcName.contains("{")&&funcName.contains("}")){
            last = "){";
        }
        String[] args = funcName.substring(funcName.indexOf("(")+1,funcName.lastIndexOf(last)).split(",");
        args = setThat(args);
        for(int i = 0;i<args.length;i++){
            if(!args[i].isEmpty()){
                args[i] = executeFunction(args[i].trim()).toString();
            }
        }
        String before = funcName;
        funcName = funcName.replaceAll("\\(([\\s\\S]+|[\\s\\S]*)\\)","");
        String funcCode = "";
        //native方法提供了funcCode
        if(before.matches(Memory.matches.get("ffunc"))){
            funcCode = funcName.substring(funcName.indexOf("{")+1,funcName.indexOf("}"));
            funcName = before.substring(0,before.indexOf("{")).replaceAll("\\(([\\s\\S]+|[\\s\\S]*)\\)","");
            args = Arrays.copyOf(args,args.length+1);
            args[args.length-1] = funcCode;
        }
        Function function = functions.get(funcName);
        if(function==null){
            return "error:no such function!"+funcName+"on "+func.indexOf(funcName)+" error";
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
            if(!alls[i].isEmpty()){
                vars_func.put(alls[i],Var.compile(alls[i]+"="+args[i]));
            }
        }

        String code = function.getCode();

        String[] codes =code.split(";");

        for(int i = 0;i<codes.length-1;i++) {
            EmeraldScript_JavaParser.parse(codes[i],vars_func);
        }
        return setThat(EmeraldScript_JavaParser.parse(codes[codes.length-1]))[0];
    }

    /**
     * 解析函数的返回变量
     * @param func
     * @return
     */
    private String getFunctionVarName(String func){
        if(func.matches(Memory.matches.get("func"))){
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

    /**
     * 解析函数
     * @param func
     * @return
     */
    private String defineFunction(String func){
        if(!func.matches(Memory.matches.get("dfunc"))){
            return func;
        }
        Function function = Function.compile(func);
        functions.put(function.getFunctionName(),function);
        return "create successfully";
    }


    /**
     * 解析局部变量的参数
     * @param command
     * @param vars
     * @return
     */
    public static Object parse(String command, Map<String,Var> vars){

        Object result = null;
        //执行函数可以返回值
        //a=echo()
        /* 定义函数 */
        result = getScript().defineFunction(command);
        if(!result.equals(command)){
            return result;
        }

        /* 将变量设置 */
        result = script.parseVar(script.unset(command,vars),vars);
        if(!result.equals(command)){
            return result;
        }

        result = script.listVar(command);
        if(!result.equals(command)){
            return result;
        }
        /* 执行函数，并设置返回值 */
        result = script.executeFunction(command);

        if(result.toString().startsWith("error:")){
            return result;
        }

        /* 获取返回值 */
        String varName = script.getFunctionVarName(command);
        if(varName!=null){
            result = script.parseVar(varName,result.toString(),vars,varName+"="+result.toString()).getValue();
        }

        return result;
    }

    public static String parse(String command){
        return parse(command,script.vars).toString();
    }

    /**
     * 给命令参数设置变量
     * @param args
     * @return
     */
    public static String[] setThat(String... args){
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



    public static EmeraldScript_JavaParser getScript() {
        return script;
    }

    public Map<String, Var> getVars() {
        return vars;
    }
}
