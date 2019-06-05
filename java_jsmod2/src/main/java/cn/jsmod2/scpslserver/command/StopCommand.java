package cn.jsmod2.scpslserver.command;

import cn.jsmod2.scpslserver.CommandSender;
import cn.jsmod2.scpslserver.Server;

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
