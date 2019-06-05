package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.utils.server.Connection;

public class DisconnectEvent extends ConnectionEvent {

    public DisconnectEvent(Connection connection){
        super(connection);
    }

    public DisconnectEvent(){

    }
}
