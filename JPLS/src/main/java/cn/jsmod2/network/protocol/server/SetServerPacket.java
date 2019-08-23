package cn.jsmod2.network.protocol.server;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetServerPacket extends SetPacket {

    public SetServerPacket(int id) {
        super(id);
        requester.with("type","cn.jsmod2.server");
    }


}
