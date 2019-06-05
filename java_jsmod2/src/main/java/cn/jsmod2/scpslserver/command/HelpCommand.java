package cn.jsmod2.scpslserver.command;

import cn.jsmod2.scpslserver.CommandSender;
import cn.jsmod2.scpslserver.Server;

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
