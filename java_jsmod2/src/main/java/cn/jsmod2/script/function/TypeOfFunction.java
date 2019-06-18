package cn.jsmod2.script.function;

import cn.jsmod2.script.Var;
import cn.jsmod2.utils.Utils;

import java.util.Map;

import static cn.jsmod2.script.EmeraldScriptVM.getScript;

public class TypeOfFunction extends NativeFunction{

    public TypeOfFunction() {
        super("typeof", "native func typeof(a)");
    }

    @Override
    public Object execute(String[] objs,Object... args) {
        Map<String,Var> vars = (Map<String, Var>) args[0];
        if(objs[0].toString().matches("[*]+[\\s\\S]+")){
            return getScript().findVar(objs[0],vars);
        }
        Var var = vars.get(objs[0]==null?"NULL":objs[0]);
        String type = var==null?(Var.compile("nick"+"="+objs[0]).getType()):var.getType();

        Utils.getMessageSender().info(type);
        return "'"+type+"'";
    }
}
