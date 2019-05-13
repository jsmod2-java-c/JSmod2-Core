package net.noyark.scpslserver.jsmod2.command;

import net.noyark.CommandSender;
import net.noyark.scpslserver.jsmod2.Server;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class HelpCommand extends NativeCommand {
    public HelpCommand() {
        super("help","all","prop:cmd.help");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Server.getSender().getServer().help();
        return true;
    }
}
