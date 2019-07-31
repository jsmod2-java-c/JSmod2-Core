package cn.jsmod2.network.protocol.event.packet;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class EventGetPacket extends GetPacket {
    public EventGetPacket(int id,Class clz) {
        super(id,clz);
        requester.with("type","event");
    }
}
