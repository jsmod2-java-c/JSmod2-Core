package cn.jsmod2.network.protocol.server;


import cn.jsmod2.network.SimpleFieldStream;

public class SimpleServerFieldStream extends SimpleFieldStream {

    public static final int ID = 197;

    public SimpleServerFieldStream( Class<?> type) {
        super(ID, type);
    }
}
