package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.Smod2Server;

public class SetServerNameEvent extends ServerEvent {

    public SetServerNameEvent(){

    }

    public SetServerNameEvent(Smod2Server server){
        super(server);
    }
}
