package cn.jsmod2.script.function;

import cn.jsmod2.script.Memory;

import java.util.Arrays;

public abstract class Function extends Memory {

    private String functionName;

    private String code;

    private String[] args;

    Function(String functionName,String code) {
        this.functionName = functionName;
        this.code = code;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setArgs(String... args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }


    public static Function compile(String func){
        String[] alls = func.split(" ");
        String[] name_start = alls[1].split(";");
        String name = name_start[0].replaceAll("\\([\\s\\S]+\\)","");
        Function function = new Function("",func.replaceAll(matches.get("startfunc"),"").replace(":end","")) {};
        function.functionName = name.replaceAll("\\(([\\s\\S]+|)\\)","");
        String[] args = name_start[0].substring(name_start[0].indexOf("(")+1,name_start[0].indexOf(")")).split(",");
        function.setArgs(args);
        return function;
    }

    @Override
    public String toString() {
        String argsString =  Arrays.toString(args);
        return "func "+functionName+"("+argsString.substring(argsString.indexOf("[")+1,argsString.indexOf("]"))+");start:"+code+":end";
    }
}
