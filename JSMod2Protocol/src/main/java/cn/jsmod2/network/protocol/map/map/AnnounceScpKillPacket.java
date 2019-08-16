package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.api.player.Player;

public class AnnounceScpKillPacket extends SetMapPacket {

    public static final int ID = 146;

    public String scpNumber;

    public Player killer;

    public AnnounceScpKillPacket(int id) {
        super(id);
    }

    @Override
    public void send() {
        requester.with("scpNumber",scpNumber).with("killer",killer).to();
    }
}
