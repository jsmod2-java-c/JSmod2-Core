package cn.jsmod2.script.function;

import cn.jsmod2.ex.TypeErrorException;
import cn.jsmod2.script.EmeraldScript_JavaParser;

public class RegisterNativeFunction extends NativeFunction {

    public RegisterNativeFunction() {
        super("register", "native func register(method)");
    }

    @Override
    public Object execute(Object... objs) {
        try{
            Object obj = Class.forName(objs[0].toString()).newInstance();
            if(obj instanceof NativeFunction){
                NativeFunction function = (NativeFunction)obj;
                EmeraldScript_JavaParser.getScript().getFunctions().put(function.getFunctionName(),function);
            }else{
                throw new TypeErrorException("the class type must be NativeFunction");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "registered";
    }
}
