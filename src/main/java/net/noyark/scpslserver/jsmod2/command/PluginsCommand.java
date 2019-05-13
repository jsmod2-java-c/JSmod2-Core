package net.noyark.scpslserver.jsmod2.command;

import net.noyark.CommandSender;
import net.noyark.scpslserver.jsmod2.Server;

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
        return false;
    }
}
