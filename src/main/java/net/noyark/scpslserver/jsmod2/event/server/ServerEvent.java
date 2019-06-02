package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.Smod2Server;
import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.event.Event;

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
