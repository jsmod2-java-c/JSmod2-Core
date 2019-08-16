package cn.jsmod2.network.protocol.map.map;


public class ClearBroadcastsPacket extends SetMapPacket {

    public static final int ID = 148;

    public ClearBroadcastsPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with(DO,"clearBroadcasts").to();
    }
}
