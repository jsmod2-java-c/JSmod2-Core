package net.noyark.scpslserver.jsmod2.network.command;

import net.noyark.Smod2Server;
import net.noyark.scpslserver.jsmod2.Server;

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
