package net.noyark.scpslserver.jsmod2.command;

import net.noyark.scpslserver.jsmod2.CommandSender;
import net.noyark.scpslserver.jsmod2.Server;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class StopCommand extends NativeCommand {

    public StopCommand() {
        super("stop","console", "prop:cmd.stop");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Server.getSender().getServer().close();
        return true;
    }
}
