package net.noyark.scpslserver.jsmod2.command;

import net.noyark.scpslserver.jsmod2.CommandSender;
import net.noyark.scpslserver.jsmod2.Server;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class ReloadCommand extends NativeCommand{

    public ReloadCommand() {
        super("reload","admin","prop:cmd.reload");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Server.getSender().getServer().reload();
        return true;
    }
}
