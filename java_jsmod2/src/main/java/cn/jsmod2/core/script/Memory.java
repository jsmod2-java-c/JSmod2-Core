package cn.jsmod2.core.script;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Memory {

    public static final Map<String,String> matches;

    public static final List<String> command = new ArrayList<>();

    private static Map<String,String> scriptPattern = new HashMap<>();

    public static void registerScriptPattern(){
        //关于变量的正则
        scriptPattern.put("var","(global::|J::)*(const )*[\\*]*+[a-z0-9A-Z_]+(=|:\\*)[\\s\\S]+");
        scriptPattern.put("list","list");
        scriptPattern.put("unset","unset [a-z0-9A-Z_]+(=[\\s\\S]+)*");
        scriptPattern.put("func","([a-z0-9A-Z_]=)*[_/\\\\A-Za-z0-9]+\\(([\\s\\S]+|[\\s\\S]*)\\)(\\{([\\s\\S]+|)\\})*");
        scriptPattern.put("dfunc","func [\\s\\S]+\\(([\\s\\S]*|[\\s\\S]+)\\);start:[\\s\\S]+:end");
        scriptPattern.put("startfunc","func [\\s\\S]+\\(([\\s\\S]+|)\\);start:");
        scriptPattern.put("pc","let [a-zA-Z0-9_]+=[\\s\\S]+");
        scriptPattern.put("ffunc","(([\\*]+|)[a-z0-9A-Z_]=)*[\\s\\S]+\\(([\\s\\S]+|[\\s\\S]*)\\)\\{([\\s\\S]+|)\\}");
        scriptPattern.put("start","([\\s\\S]+|)\\{");
        scriptPattern.put("ptr","(global::|J::)*(const )*[a-z0-9A-Z_]+:\\*[\\s\\S]+");
        //scriptPattern.put("if","if\\[[\\s\\S]+\\](([\\s\\S]+)|\\{[.]+\\})((elif\\[[\\s\\S]+\\]([\\s\\S]+)|else\\{([\\s\\S]+;)+\\})+|elif\\[[\\s\\S]+\\]\\{([\\s\\S]+)+\\}|)");
    }

    static {
        registerScriptPattern();
        command.add("func");
        command.add("start");
        command.add("end");
        command.add("NULL");
        command.add("unset");
        command.add("list");
        command.add("let");
        matches = scriptPattern;
    }
}
