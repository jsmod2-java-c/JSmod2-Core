package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public class AnnounceCustomMessagePacket extends SetPacket {

    public static final int ID = 144;

    public String words;

    public AnnounceCustomMessagePacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("words",words).to();
    }
}
