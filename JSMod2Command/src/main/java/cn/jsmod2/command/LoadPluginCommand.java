package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.command.NativeCommand;

public class LoadPluginCommand extends NativeCommand {

    public LoadPluginCommand() {
        super("load", Powers.CONSOLE,"prop:cmd.load");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        for(String str:strings){
            Server.getSender().getServer().getPluginManager().getPluginClassLoader().loadPlugin(str);
        }
        return true;
    }
}
