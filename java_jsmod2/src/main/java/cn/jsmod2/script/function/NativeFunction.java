package cn.jsmod2.script.function;

public abstract class NativeFunction<T> extends Function {


    NativeFunction(String functionName,String code) {
        super(functionName,code);
    }

    public abstract T execute(String[] args,Object... objs);
}
