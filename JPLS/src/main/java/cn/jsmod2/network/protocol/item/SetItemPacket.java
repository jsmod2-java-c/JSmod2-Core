package cn.jsmod2.network.protocol.item;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetItemPacket extends SetPacket {



    public SetItemPacket(int id) {
        super(id);
        requester.with("type","cn.jsmod2.item");
    }


}
