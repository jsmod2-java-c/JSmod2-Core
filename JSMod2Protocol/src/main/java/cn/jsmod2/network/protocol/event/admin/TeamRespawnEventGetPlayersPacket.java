package cn.jsmod2.network.protocol.event.admin;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.network.protocol.event.packet.EventGetPacket;

import java.util.List;

public class TeamRespawnEventGetPlayersPacket extends EventGetPacket {

    public static final int ID = 184;

    public TeamRespawnEventGetPlayersPacket() {
        super(ID, Player.class);
    }

    @Override
    public List send() {
        return requester.with("field","players").get().getProtocolArray(false);
    }
}
