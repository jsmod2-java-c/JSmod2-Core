package cn.jsmod2.network.protocol.event.admin;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.network.protocol.event.packet.EventSetPacket;

import java.util.ArrayList;
import java.util.List;

public class TeamRespawnEventSetPlayerListPacket extends EventSetPacket {

    public static final int ID = 185;

    public List<IPlayer> players;

    public TeamRespawnEventSetPlayerListPacket() {
        super(ID);
    }

    @Override
    public void send() {
        List<String> ids = new ArrayList<>();
        for(IPlayer player:players){
            ids.add(((ApiId)player).getApiId());
        }
        requester.with("players", Utils.arraysToString((String[]) ids.toArray())).to();
    }
}
