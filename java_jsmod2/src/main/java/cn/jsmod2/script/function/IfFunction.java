package cn.jsmod2.script.function;

import cn.jsmod2.script.EmeraldScriptVM;
import cn.jsmod2.script.Var;

import java.util.Arrays;
import java.util.HashMap;

public class IfFunction extends NativeFunction{

    public IfFunction() {
        super("if","native func if(boolean){}");
    }

    /**
     * if(boolean b){
     *  right(){
     *
     *  }elif(){
     *
     *  }else{
     *
     *  };;
     * }
     * @param objs
     * @return
     */
    @Override
    public Object execute(Object... objs) {
        if(objs.length!=2){
            return "error-the if must have one boolean expression";
        }
        boolean resultBoolean = false;
        String expression = objs[0].toString();
        if(expression.equals("1")){
            resultBoolean = true;
        }
        String code = objs[1].toString();
        HashMap<String, Var> vars = new HashMap<>();
        vars.putAll(EmeraldScriptVM.getScript().getVars());

        return resultBoolean;
    }
}
