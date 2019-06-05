package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.server.Connection;
import cn.jsmod2.scpslserver.event.Event;

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
    /** java-bean */
    @UseForServerInit
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
