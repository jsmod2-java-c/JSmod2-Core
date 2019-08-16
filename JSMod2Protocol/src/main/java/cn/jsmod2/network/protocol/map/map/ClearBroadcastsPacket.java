package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public class ClearBroadcastsPacket extends SetPacket {

    public static final int ID = 148;

    public ClearBroadcastsPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with(DO,"clearBroadcasts").to();
    }
}
