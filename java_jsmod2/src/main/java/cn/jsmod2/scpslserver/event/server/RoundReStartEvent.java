package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.Smod2Server;


public class RoundReStartEvent extends ServerEvent{

    public RoundReStartEvent(){

    }

    public RoundReStartEvent(Smod2Server server){
        super(server);
    }

}
