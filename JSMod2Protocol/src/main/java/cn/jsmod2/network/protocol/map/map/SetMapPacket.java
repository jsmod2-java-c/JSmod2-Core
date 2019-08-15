package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetMapPacket extends SetPacket {

    public SetMapPacket(int id) {
        super(id);
        requester.with("type","map");
    }
}
