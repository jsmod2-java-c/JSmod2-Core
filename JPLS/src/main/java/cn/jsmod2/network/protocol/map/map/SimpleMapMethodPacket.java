package cn.jsmod2.network.protocol.map.map;


import cn.jsmod2.network.SimpleMethodStream;

public class SimpleMapMethodPacket extends SimpleMethodStream {

    public static final int ID = 196;

    public SimpleMapMethodPacket( Class<?> type) {
        super(ID, type);
    }


}
