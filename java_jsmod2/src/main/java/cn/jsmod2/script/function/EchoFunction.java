package cn.jsmod2.script.function;


public class EchoFunction extends NativeFunction {

    public EchoFunction() {
        super("echo","native func echo(a)");
        setCode("code");
    }

    @Override
    public Void execute(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for(Object o:objects){
            builder.append(o.toString());
        }
        System.out.println(builder.toString().replaceAll("'",""));
        return null;
    }
}
