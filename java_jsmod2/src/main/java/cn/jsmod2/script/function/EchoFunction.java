package cn.jsmod2.script.function;

import java.util.Arrays;

public class EchoFunction extends NativeFunction {

    public EchoFunction() {
        super("echo","native func echo(a)");
        setCode("code");
    }

    @Override
    public Void execute(Object... objects) {
        System.out.println(Arrays.toString(objects));
        return null;
    }
}
