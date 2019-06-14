package cn.jsmod2.script;

import cn.jsmod2.Register;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Memory {

    public static final Map<String,String> matches = Register.getInstance().getScriptPettern();

    public static final List<String> command = new ArrayList<>();

    static {
        command.add("func");
        command.add("start");
        command.add("end");
        command.add("nick");
        command.add("NULL");
        command.add("unset");
        command.add("list");
    }
}
