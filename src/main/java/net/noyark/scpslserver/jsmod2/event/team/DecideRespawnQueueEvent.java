package net.noyark.scpslserver.jsmod2.event.team;

import net.noyark.scpslserver.jsmod2.utils.team.Team;
import net.noyark.scpslserver.jsmod2.event.Event;

public class DecideRespawnQueueEvent extends Event {

    private Team[] teams;

    public DecideRespawnQueueEvent(Team[] teams){
        this.teams = teams;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }
}
