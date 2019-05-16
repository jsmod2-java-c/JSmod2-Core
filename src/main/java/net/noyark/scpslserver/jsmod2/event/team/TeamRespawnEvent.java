package net.noyark.scpslserver.jsmod2.event.team;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

import java.util.List;

public class TeamRespawnEvent extends Event {

    private List<Player> playerList;

    private boolean spawnChaos;

    public TeamRespawnEvent(List<Player> playerList,boolean isCI){
        this.playerList = playerList;
        this.spawnChaos = isCI;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public boolean isSpawnChaos() {
        return spawnChaos;
    }

    public void setSpawnChaos(boolean spawnChaos) {
        this.spawnChaos = spawnChaos;
    }
}
