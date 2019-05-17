package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.utils.server.Connection;

public abstract class ConnectionEvent extends Event {

    private Connection connection;

    public ConnectionEvent(Connection connection){
        this.connection = connection;
    }

    public ConnectionEvent(){

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
