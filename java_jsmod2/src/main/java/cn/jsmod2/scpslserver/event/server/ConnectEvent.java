package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.utils.server.Connection;

public class ConnectEvent extends ConnectionEvent {

    public ConnectEvent(Connection connection){
        super(connection);
    }


}
