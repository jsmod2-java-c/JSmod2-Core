package cn.jsmod2.network.protocol.server;

import cn.jsmod2.api.player.Player;

import java.util.List;

public class GetPlayerPacket extends GetServerPacket {


    public GetPlayerPacket() {
        super(0x63, Player.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> send() {
        return (List<Player>)requester.with("field","players").get().getArray();
    }
}
