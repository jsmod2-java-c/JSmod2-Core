package cn.jsmod2.network.protocol.item;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetItemPacket extends GetPacket {

    public GetItemPacket(int id,Class<?> type) {
        super(id,type);
        requester.with("type","item");
    }

    public String playerName;

}
