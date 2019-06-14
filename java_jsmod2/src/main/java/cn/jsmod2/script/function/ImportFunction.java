package cn.jsmod2.script.function;

import cn.jsmod2.script.Jsmod2Script;

import java.io.IOException;

public class ImportFunction extends NativeFunction {

    public ImportFunction() {
        super("import", "native func import(file)");
    }

    @Override
    public Object execute(Object... objs) {
        try{
            Jsmod2Script.getScript().importFile(objs[0].toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return objs[0];
    }
}
