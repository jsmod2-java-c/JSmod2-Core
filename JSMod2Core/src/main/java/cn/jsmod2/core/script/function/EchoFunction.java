package cn.jsmod2.core.script.function;


public class EchoFunction extends NativeFunction {

    public EchoFunction() {
        super("echo","native func echo(a)");
        setCode("code");
    }

    @Override
    public Void execute(String[] objects,Object... objs) {
        StringBuilder builder = new StringBuilder();
        for(Object o:objects){
            builder.append(o.toString());
        }

        System.out.println(builder);
        return null;
    }
}
