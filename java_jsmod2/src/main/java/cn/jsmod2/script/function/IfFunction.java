package cn.jsmod2.script.function;

import cn.jsmod2.script.EmeraldScript_JavaParser;
import cn.jsmod2.script.Var;

import java.util.HashMap;

public class IfFunction extends NativeFunction{

    public IfFunction() {
        super("if","native func if(boolean){}");
    }

    @Override
    public Object execute(Object... objs) {
        boolean resultBoolean = false;
        String expression = objs[0].toString();
        if(expression.equals("1")){
            resultBoolean = true;
        }
        String code = objs[1].toString();
        HashMap<String, Var> vars = new HashMap<>();
        vars.putAll(EmeraldScript_JavaParser.getScript().getVars());
        String[] codes = code.split(";");
        if(resultBoolean){
            for(String c:codes) {
                EmeraldScript_JavaParser.parse(c, vars,EmeraldScript_JavaParser.getScript().getVars());
            }
        }
        return resultBoolean;
    }
}
