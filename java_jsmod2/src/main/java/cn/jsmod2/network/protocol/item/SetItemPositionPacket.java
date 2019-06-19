package cn.jsmod2.network.protocol.item;


import cn.jsmod2.math.Vector;

public class SetItemPositionPacket extends SetItemPacket {



    public SetItemPositionPacket() {
        super(0x5b);
    }

    private static final String POSITION = "position";

    public Vector position;

    @Override
    public void send() {
        requester
                .with(POSITION,position)
                .end(playerName)
                .to(this);
    }
}
