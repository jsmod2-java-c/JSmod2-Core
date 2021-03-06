/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.team;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.protocol.event.admin.TeamRespawnEventGetPlayersPacket;
import cn.jsmod2.network.protocol.event.admin.TeamRespawnEventSetPlayerListPacket;

import java.util.ArrayList;
import java.util.List;

public class TeamRespawnEvent extends Event implements ITeamRespawnEvent{

    private List<Player> playerList = new ArrayList<>();

    private boolean spawnChaos;

    public TeamRespawnEvent(List<Player> playerList, boolean isCI){
        this.playerList = playerList;
        this.spawnChaos = isCI;
    }
    public TeamRespawnEvent(){

    }

    @SuppressWarnings("unchecked")
    public List<Player> getPlayerList() {
        if(playerList == null){
            TeamRespawnEventGetPlayersPacket packet = new TeamRespawnEventGetPlayersPacket();
            packet.playerName = playerName;
            playerList = packet.send();
        }
        return playerList;
    }

    public void setPlayerList(List<IPlayer> playerList) {
        this.playerList.clear();
        for(IPlayer p : playerList){
            this.playerList.add((Player)p);
        }
        TeamRespawnEventSetPlayerListPacket packet = new TeamRespawnEventSetPlayerListPacket();
        packet.playerName = playerName;
        packet.players = playerList;
        packet.send();
    }

    public boolean isSpawnChaos() {
        spawnChaos = PacketSender.sendEventGetPacket(playerName,"SpawnChaos",Boolean.class);
        return spawnChaos;
    }

    public void setSpawnChaos(boolean spawnChaos) {
        PacketSender.sendEventSetPacket(playerName,"SpawnChaos",spawnChaos);
        this.spawnChaos = spawnChaos;
    }
}
