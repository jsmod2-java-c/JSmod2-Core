package cn.jsmod2.network.protocol.map.generator;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetGeneratorPacket extends GetPacket {

    public GetGeneratorPacket(int id, Class<?> type) {
        super(id, type);
        requester.with("type","generator");
    }
}
