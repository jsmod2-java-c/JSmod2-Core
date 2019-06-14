package cn.jsmod2.script;

import cn.jsmod2.Register;

import java.util.HashMap;
import java.util.Map;

/**
 * Jsmod2服务器脚本的解析器
 * script进入脚本解析页面
 */
public class Jsmod2Script {

    static {
        script = new Jsmod2Script();
    }

    private static Jsmod2Script script;

    private Map<String,String> matches = Register.getInstance().getScriptPettern();


    //全局变量
    private Map<String,Var> vars = new HashMap<>();

    //a=0
    private String parseVar(String cmd){
        if(!cmd.matches(matches.get("var"))){
            return cmd;
        }
        String[] key_value = cmd.split("=");
        if(vars.get(key_value[0])!=null){
            Var var = vars.get(key_value[0]);
            var.setValue(key_value[1]);
            return key_value[0]+var.toString();
        }else{
            Var var = new Var(key_value[1]);
            vars.put(key_value[0],var);
            return cmd;
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

    public static String parse(String command){
        StringBuilder builder = new StringBuilder();
        builder.append(script.parseVar(script.unset(command)));
        builder.append(script.listVar(command));
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

}
