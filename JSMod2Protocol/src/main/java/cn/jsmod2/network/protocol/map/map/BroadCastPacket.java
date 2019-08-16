package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public class BroadCastPacket extends SetPacket {

    public int duration;
    public String message;
    public boolean isMonoSpaced;

    public static final int ID = 147;

    public BroadCastPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("duration",duration).with("message",message).with("isMonoSpaced",isMonoSpaced).to();
    }
}
