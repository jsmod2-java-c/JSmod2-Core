package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.command.NativeCommand;

/**
 * 测试异常
 */
@Deprecated
public class ThrowCommand extends NativeCommand {

    public ThrowCommand() {
        super("throw","just throw");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {

        try{
            throw (RuntimeException) (Class.forName(args[0]).newInstance());
        }catch (ClassNotFoundException|InstantiationException|IllegalAccessException e){
            return false;
        }
    }
}
