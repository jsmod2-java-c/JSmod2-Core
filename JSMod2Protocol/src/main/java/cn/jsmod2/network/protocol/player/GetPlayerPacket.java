package cn.jsmod2.network.protocol.player;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetPlayerPacket extends GetPacket {

    public GetPlayerPacket(int id, Class<?> type) {
        super(id, type);
        requester.with("type","player");
    }
}
