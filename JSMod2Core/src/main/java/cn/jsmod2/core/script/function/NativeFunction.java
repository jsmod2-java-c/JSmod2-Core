package cn.jsmod2.core.script.function;

public abstract class NativeFunction<T> extends Function {


    public NativeFunction(String functionName,String code) {
        super(functionName,code);
    }

    public abstract T execute(String[] args,Object... objs);
}
