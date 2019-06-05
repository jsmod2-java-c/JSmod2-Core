package cn.jsmod2.scpslserver.command;

import cn.jsmod2.scpslserver.CommandSender;
import cn.jsmod2.scpslserver.Server;

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
