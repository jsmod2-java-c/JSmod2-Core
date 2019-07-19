package cn.jsmod2.core.script.function;

import cn.jsmod2.core.script.EmeraldScriptVM;

import java.io.IOException;

public class ImportFunction extends NativeFunction {

    public ImportFunction() {
        super("import", "native func import(file)");
    }

    @Override
    public Object execute(String[] objs,Object... objects) {

        try{
            EmeraldScriptVM.getVM().importFile(EmeraldScriptVM.getVM().getVars().get("ENV_FILE").getValue()+"/"+objs[0]);
        }catch (IOException e){
            e.printStackTrace();
        }
        return objs[0];
    }
}
