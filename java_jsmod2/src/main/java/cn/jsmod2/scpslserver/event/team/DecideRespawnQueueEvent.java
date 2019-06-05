package cn.jsmod2.scpslserver.event.team;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.team.Team;
import cn.jsmod2.scpslserver.event.Event;

public class DecideRespawnQueueEvent extends Event {

    private Team[] teams;

    public DecideRespawnQueueEvent(Team[] teams){
        this.teams = teams;
    }

    public Team[] getTeams() {
        return teams;
    }

    /** java-bean */
    @UseForServerInit
    public void setTeams(Team[] teams) {
        this.teams = teams;
    }
}
