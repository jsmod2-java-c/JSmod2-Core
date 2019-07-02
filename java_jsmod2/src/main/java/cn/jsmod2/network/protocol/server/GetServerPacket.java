package cn.jsmod2.network.protocol.server;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetServerPacket extends GetPacket {


    public GetServerPacket(int id, Class type) {
        super(id, type);
        requester.with("type","server");
    }
}
