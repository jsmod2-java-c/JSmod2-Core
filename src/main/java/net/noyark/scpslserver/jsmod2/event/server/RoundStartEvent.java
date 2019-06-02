package net.noyark.scpslserver.jsmod2.event.server;


import net.noyark.scpslserver.jsmod2.Smod2Server;

public class RoundStartEvent extends ServerEvent {

    public RoundStartEvent(Smod2Server server){
        super(server);
    }

    public RoundStartEvent(){

    }
}
