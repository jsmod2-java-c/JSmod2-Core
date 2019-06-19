package cn.jsmod2.network.protocol.item;


import cn.jsmod2.math.Vector;

public class SetItemPositionPacket extends SetItemPacket {

    private static final String POSITION = "position";

    public Vector position;

    @Override
    public void send() {
        server.getRequester()
                .with(POSITION,position)
                .end(playerName)
                .to(this);
    }
}
