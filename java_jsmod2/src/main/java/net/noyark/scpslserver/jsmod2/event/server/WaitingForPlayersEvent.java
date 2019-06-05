package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.Smod2Server;

public class WaitingForPlayersEvent extends ServerEvent{

    public WaitingForPlayersEvent(Smod2Server server){
        super(server);
    }

    public WaitingForPlayersEvent(){

    }
}
