package cn.jsmod2.network.protocol.map.door;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetDoorPacket extends SetPacket {

    public SetDoorPacket(int id) {
        super(id);
        requester.with("type","door");
    }
}
