package net.noyark.scpslserver.jsmod2.command;

import net.noyark.scpslserver.jsmod2.CommandSender;
import net.noyark.scpslserver.jsmod2.Server;

public class RebootCommand extends NativeCommand{

    public RebootCommand() {
        super("reboot","admin","prop:cmd.reboot");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Server.getSender().getServer().reboot();
        return true;
    }
}
