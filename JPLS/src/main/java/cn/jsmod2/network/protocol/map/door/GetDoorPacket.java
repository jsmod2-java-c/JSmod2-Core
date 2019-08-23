package cn.jsmod2.network.protocol.map.door;

import cn.jsmod2.core.protocol.GetPacket;

//108
public abstract class GetDoorPacket extends GetPacket {



    public GetDoorPacket(int id, Class<?> type) {
        super(id, type);
        requester.with("type","door");
    }
}
