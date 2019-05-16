package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.Smod2Server;


public class RoundReStartEvent extends ServerEvent{

    public RoundReStartEvent(){

    }

    public RoundReStartEvent(Smod2Server server){
        super(server);
    }

}
