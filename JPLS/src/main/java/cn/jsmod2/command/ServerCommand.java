package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.log.ServerLogger;

import static cn.jsmod2.core.utils.Utils.contains;

/**
 * 用于调试服务器而定义的命令格式
 */
public class ServerCommand extends NativeCommand {

    public ServerCommand() {
        super("server", Powers.ADMIN,"the server debug command");
    }

    //-f getPlayers
    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        if(contains(strings,"-p")){
            ServerLogger.getLogger().multiInfo(getClass(),Server.getRuntime().running().getGameServer().getPlayers()==null?"isNull":"notNull","","");
        }else if(contains(strings,"-ip")){
            ServerLogger.getLogger().multiInfo(getClass(),Server.getRuntime().running().getGameServer().getIpAddress(),"","");
        }
        return true;
    }
}
