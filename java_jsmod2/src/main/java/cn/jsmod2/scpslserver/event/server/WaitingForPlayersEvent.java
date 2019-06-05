package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.Smod2Server;

public class WaitingForPlayersEvent extends ServerEvent{

    public WaitingForPlayersEvent(Smod2Server server){
        super(server);
    }

    public WaitingForPlayersEvent(){

    }
}
