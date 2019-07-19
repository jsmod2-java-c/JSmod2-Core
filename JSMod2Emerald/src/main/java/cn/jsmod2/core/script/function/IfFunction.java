package cn.jsmod2.core.script.function;

import cn.jsmod2.core.script.EmeraldScriptVM;
import cn.jsmod2.core.script.Var;

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
    public Object execute(String[] objs,Object... objects) {
        if(objs.length!=3){
            return "error-the if must have one boolean expression";
        }
        boolean resultBoolean = false;
        String expression = objs[0];
        if(expression.equals("1")){
            resultBoolean = true;
        }
        String code = objs[1].toString();
        HashMap<String, Var> vars = new HashMap<>();
        vars.putAll(EmeraldScriptVM.getVM().getVars());

        return resultBoolean;
    }
}
