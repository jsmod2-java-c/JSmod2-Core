package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.command.NativeCommand;

public class UnloadPluginCommand extends NativeCommand {

    public UnloadPluginCommand() {
        super("unload", Powers.CONSOLE,"prop:cmd.unload");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        if(strings.length>0) {
            for(String str:strings) {
                Server.getSender().getServer().getPluginManager().getPluginClassLoader().unloadPlugin(str);
            }
            return true;
        }
        return false;
    }
}
