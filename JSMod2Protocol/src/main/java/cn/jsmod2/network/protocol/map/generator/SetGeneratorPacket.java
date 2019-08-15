package cn.jsmod2.network.protocol.map.generator;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetGeneratorPacket extends SetPacket {

    public SetGeneratorPacket(int id) {
        super(id);
        requester.with("type","generator");
    }
}
