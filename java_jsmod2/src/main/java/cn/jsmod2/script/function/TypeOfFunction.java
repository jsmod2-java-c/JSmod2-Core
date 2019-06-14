package cn.jsmod2.script.function;

import cn.jsmod2.script.Jsmod2Script;
import cn.jsmod2.utils.Utils;

public class TypeOfFunction extends NativeFunction{

    public TypeOfFunction() {
        super("typeof", "native func typeof(a)");
    }

    @Override
    public Object execute(Object... objs) {
        String type = Jsmod2Script.getScript().getVars().get(objs[0].toString()).getType();
        Utils.getMessageSender().info(type);
        return type;
    }
}
