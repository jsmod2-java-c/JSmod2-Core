package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.utils.server.Connection;

public class ConnectEvent extends ConnectionEvent {

    public ConnectEvent(Connection connection){
        super(connection);
    }


}
