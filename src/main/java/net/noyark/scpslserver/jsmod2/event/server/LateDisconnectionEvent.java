package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.utils.server.Connection;

public class LateDisconnectionEvent extends ConnectionEvent {

    public LateDisconnectionEvent(Connection connection){
        super(connection);
    }

    public LateDisconnectionEvent(){

    }
}
