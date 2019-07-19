package cn.jsmod2.core.script.function;

public class StringAddFunction extends NativeFunction {

    public StringAddFunction() {
        super("+", "native func +(*args...:String)");
    }

    @Override
    public Object execute(String[] objs,Object... args) {
        return objs[0].replaceAll("'\\+'","");
    }
}
