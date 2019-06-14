package cn.jsmod2.script;

import cn.jsmod2.Register;
import cn.jsmod2.script.function.EchoFunction;
import cn.jsmod2.script.function.Function;
import cn.jsmod2.script.function.NativeFunction;
import cn.jsmod2.script.function.TypeOfFunction;

import java.util.HashMap;
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
    }

    private static Jsmod2Script script;

    private Map<String,String> matches = Register.getInstance().getScriptPettern();

    private Map<String, Function> functions = new HashMap<>();


    //全局变量
    private Map<String,Var> vars = new HashMap<>();

    //a=0
    private Object parseVar(String cmd){
        if(!cmd.matches(matches.get("var"))){
            return "";
        }
        String[] key_value = cmd.split("=");
        return parseVar(key_value[0],key_value[1]);
    }

    private Var parseVar(String key,String value){
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

    private String unset(String command){
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

    private Object executeFunction(String func){
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
        funcName = funcName.replace("f::","").replaceAll("\\(([\\s\\S]+|[\\s\\S]*)\\)","");
        Function function = functions.get(funcName);
        if(function instanceof NativeFunction){
            return ((NativeFunction) function).execute(args);
        }
        //普通函数还没开始处理
        return "";
    }

    public String getFunctionVarName(String func){
        if(func.matches(matches.get("func"))){
            String[] strs = func.split("=");
            if(strs.length==2){
                return strs[0];
            }
        }
        return null;
    }

    public static String parse(String command){
        StringBuilder builder = new StringBuilder();
        //执行函数可以返回值
        //a=echo()
        Object obj = script.parseVar(script.unset(command));
        builder.append(obj);
        builder.append(script.listVar(command));
        Object o = script.executeFunction(command);
        String varName = script.getFunctionVarName(command);
        if(varName!=null){
            if(obj instanceof Var){
                ((Var) obj).unset();
            }
            builder.append(script.parseVar(varName,o==null?"NULL":o.toString()));
        }

        return builder.toString();
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
