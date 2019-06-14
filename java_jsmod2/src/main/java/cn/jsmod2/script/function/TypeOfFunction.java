package cn.jsmod2.script.function;

import cn.jsmod2.script.Jsmod2Script;
import cn.jsmod2.script.Var;
import cn.jsmod2.utils.Utils;

public class TypeOfFunction extends NativeFunction{

    public TypeOfFunction() {
        super("typeof", "native func typeof(a)");
    }

    @Override
    public Object execute(Object... objs) {
        Var var = Jsmod2Script.getScript().getVars().get(objs[0]==null?"NULL":objs[0].toString());
        String type = var==null?(new Var(objs[0].toString()).getType()):var.getType();
        Utils.getMessageSender().info(type);
        return type;
    }
}
