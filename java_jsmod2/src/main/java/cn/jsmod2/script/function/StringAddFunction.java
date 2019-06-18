package cn.jsmod2.script.function;

public class StringAddFunction extends NativeFunction {

    public StringAddFunction() {
        super("+", "native func +(*args...:String)");
    }

    @Override
    public Object execute(Object... objs) {
        return objs[0].toString().replaceAll("'\\+'","");
    }
}
