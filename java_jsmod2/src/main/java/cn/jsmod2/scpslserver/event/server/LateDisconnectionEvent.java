package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.utils.server.Connection;

public class LateDisconnectionEvent extends ConnectionEvent {

    public LateDisconnectionEvent(Connection connection){
        super(connection);
    }

    public LateDisconnectionEvent(){

    }
}
