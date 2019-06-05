package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.Smod2Server;

public class SetServerNameEvent extends ServerEvent {

    public SetServerNameEvent(){

    }

    public SetServerNameEvent(Smod2Server server){
        super(server);
    }
}
