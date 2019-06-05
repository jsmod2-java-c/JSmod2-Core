package cn.jsmod2.scpslserver.command;

import cn.jsmod2.scpslserver.CommandSender;
import cn.jsmod2.scpslserver.Server;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class PluginsCommand extends NativeCommand{

    public PluginsCommand() {
        super("plugins","admin","prop:cmd.plugins");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Server.getSender().getServer().getPluginManager().plugins();
        return true;
    }
}
