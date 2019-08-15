package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetMapPacket extends GetPacket {

    public GetMapPacket(int id, Class<?> type) {
        super(id, type);
        requester.with("type","map");
    }
}
