package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.Smod2Server;
import cn.jsmod2.scpslserver.event.Event;

public abstract class ServerEvent extends Event {

    private Smod2Server server;

    public ServerEvent(Smod2Server server){
        this.server = server;
    }

    public ServerEvent(){

    }

    public Smod2Server getServer() {
        return server;
    }

    /** java-bean */
    @UseForServerInit
    public void setServer(Smod2Server server) {
        this.server = server;
    }
}
