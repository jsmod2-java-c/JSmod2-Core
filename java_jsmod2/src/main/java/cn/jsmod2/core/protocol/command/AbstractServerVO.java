package cn.jsmod2.core.protocol.command;

import cn.jsmod2.core.GameServer;
import cn.jsmod2.core.Server;

public abstract class AbstractServerVO<T extends GameServer> extends CommandVO {

    @Deprecated
    public void setServer(T server){
        Server.getSender().getServer().getGameServer().updateServer(server);
    }

    public GameServer getServer(){
        return Server.getSender().getServer().getGameServer();
    }
}
