package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.protocol.SetPacket;

public class AnnounceScpKillPacket extends SetPacket {

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
