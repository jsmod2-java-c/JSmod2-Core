package cn.jsmod2.script.function;

public class ReturnFunction extends NativeFunction{
    public ReturnFunction() {
        super("return","native func return(a...)");
    }

    @Override
    public Object execute(String[] objs,Object... objects) {
        if(objs.length==0){
            return "returned:NULL";
        }else{
            return "returned:"+objs[0];
        }
    }
}
