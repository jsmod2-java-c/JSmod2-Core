package cn.jsmod2.network.protocol.event.packet;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class EventSetPacket extends SetPacket {

    public String playerName;

    public EventSetPacket(int id) {
        super(id);
        requester.with("type","event");
    }
}
