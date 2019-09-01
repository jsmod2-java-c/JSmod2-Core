package cn.jsmod2.network.protocol.server;

import cn.jsmod2.api.player.Player;

import java.util.List;

public class GetPlayerPacket extends GetServerPacket {

    public static final int ID = 0x63;

    public String filter = "";


    public GetPlayerPacket() {
        super(ID, Player.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> send() {
        return (List<Player>)requester.with("field","players").with("filter",filter).get().getProtocolArray(false);
    }
}
