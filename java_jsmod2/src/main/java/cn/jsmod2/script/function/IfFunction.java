package cn.jsmod2.script.function;

import cn.jsmod2.script.EmeraldScriptVM;
import cn.jsmod2.script.Var;

import java.util.HashMap;

public class IfFunction extends NativeFunction{

    public IfFunction() {
        super("if","native func if(boolean){}");
    }

    /**
     * if(boolean b){
     *  right(){
     *
     *  };
     *  else(){
     *
     *  };
     *  else(boolean b){
     *
     *  };
     * }
     * @param objs
     * @return
     */
    @Override
    public Object execute(Object... objs) {
        boolean resultBoolean = false;
        String expression = objs[0].toString();
        if(expression.equals("1")){
            resultBoolean = true;
        }
        String code = objs[1].toString();
        HashMap<String, Var> vars = new HashMap<>();
        vars.putAll(EmeraldScriptVM.getScript().getVars());
        String[] codes = code.split(";");
        if(resultBoolean){
            for(String c:codes) {
                EmeraldScriptVM.parse(c, vars, EmeraldScriptVM.getScript().getVars());
            }
        }
        return resultBoolean;
    }
}
