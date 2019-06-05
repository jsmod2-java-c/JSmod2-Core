package cn.jsmod2.scpslserver.event.server;


import cn.jsmod2.scpslserver.Smod2Server;

public class RoundStartEvent extends ServerEvent {

    public RoundStartEvent(Smod2Server server){
        super(server);
    }

    public RoundStartEvent(){

    }
}
