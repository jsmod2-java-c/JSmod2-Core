package cn.jsmod2.scpslserver.network.command;

import cn.jsmod2.scpslserver.Smod2Server;

public class ServerVO extends CommandVO{

    public ServerVO(){
        super();
    }

    private Smod2Server server;

    public Smod2Server getServer() {
        return server;
    }

    public void setServer(Smod2Server server) {
        this.server = server;
    }
}
