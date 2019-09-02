package cn.jsmod2.core.protocol.command;

import cn.jsmod2.core.GameServer;
import cn.jsmod2.core.Server;

public abstract class AbstractServerVO<T extends GameServer> extends CommandVO {

    @Deprecated
    public void setServer(T server){
        //Server.getRuntime().running().getGameServer().updateServer(cn.jsmod2.server);
    }

    public GameServer getServer(){
        return Server.getRuntime().running().getGameServer();
    }
}
