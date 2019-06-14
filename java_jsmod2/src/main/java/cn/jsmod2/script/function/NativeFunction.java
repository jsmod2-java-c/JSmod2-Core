package cn.jsmod2.script.function;

public abstract class NativeFunction<T> extends Function {


    public NativeFunction(String functionName,String code) {
        super(functionName,code);
    }

    public abstract T execute(Object... objs);
}
