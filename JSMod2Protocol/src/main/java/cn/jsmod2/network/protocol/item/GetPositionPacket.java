package cn.jsmod2.network.protocol.item;


import cn.jsmod2.core.math.Vector;

public class GetPositionPacket extends GetItemPacket {

    public static final int ID = 0x5e;

    public GetPositionPacket() {
        super(ID, Vector.class);
    }

    @Override
    public Vector send() {
        return (Vector)(requester.with("field","position").with("player",playerName).get().get());
    }
}
