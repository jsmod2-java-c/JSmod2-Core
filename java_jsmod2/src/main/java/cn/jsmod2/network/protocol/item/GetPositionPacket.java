package cn.jsmod2.network.protocol.item;


import cn.jsmod2.core.math.Vector;

public class GetPositionPacket extends GetItemPacket {

    public GetPositionPacket() {
        super(0x3e, Vector.class);
    }

    @Override
    public Vector send() {
        return (Vector)(requester.with("field","position").end(playerName).get().get());
    }
}
