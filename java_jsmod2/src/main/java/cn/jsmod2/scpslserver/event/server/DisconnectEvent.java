package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.utils.server.Connection;

public class DisconnectEvent extends ConnectionEvent {

    public DisconnectEvent(Connection connection){
        super(connection);
    }

    public DisconnectEvent(){

    }
}
