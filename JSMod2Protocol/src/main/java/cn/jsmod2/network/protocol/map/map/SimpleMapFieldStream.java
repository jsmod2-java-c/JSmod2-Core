package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.network.SimpleFieldStream;

public class SimpleMapFieldStream extends SimpleFieldStream {

    public static final int ID = 196;

    public SimpleMapFieldStream(Class<?> type) {
        super(ID, type);
    }

}
