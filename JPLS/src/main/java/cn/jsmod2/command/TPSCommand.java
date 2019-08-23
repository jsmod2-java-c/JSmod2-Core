package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Message;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.utils.Utils;

public class TPSCommand extends NativeCommand {

    public TPSCommand() {
        super("tps","tps");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Utils.getMessageSender().info("SERVER-TPS:"+Server.getSender().getServer().getTPS()+"");
        return true;
    }
}
