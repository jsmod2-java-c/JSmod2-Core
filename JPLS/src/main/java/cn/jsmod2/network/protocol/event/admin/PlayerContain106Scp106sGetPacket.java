package cn.jsmod2.network.protocol.event.admin;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.network.protocol.event.packet.EventGetPacket;

public class PlayerContain106Scp106sGetPacket extends EventGetPacket {

    public static final int ID = 182;

    public PlayerContain106Scp106sGetPacket() {
        super(ID, Player.class);
    }

    @Override
    public Object send() {
        return requester.with("field","scp106s").get().getProtocolArray(false).toArray();
    }
}
