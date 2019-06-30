package cn.jsmod2.core.script.function;

import cn.jsmod2.core.ex.TypeErrorException;
import cn.jsmod2.core.script.EmeraldScriptVM;

public class RegisterNativeFunction extends NativeFunction {

    public RegisterNativeFunction() {
        super("register", "native func register(method)");
    }

    @Override
    public Object execute(String[] objs,Object... objects) {
        try{
            Object obj = Class.forName(objs[0]).newInstance();
            if(obj instanceof NativeFunction){
                NativeFunction function = (NativeFunction)obj;
                EmeraldScriptVM.getVM().getFunctions().put(function.getFunctionName(),function);
            }else{
                throw new TypeErrorException("the class type must be NativeFunction");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "registered";
    }
}
