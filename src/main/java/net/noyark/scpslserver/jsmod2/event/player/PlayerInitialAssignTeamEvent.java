package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.team.Team;

public class PlayerInitialAssignTeamEvent extends PlayerEvent {
    private Team team;

    public PlayerInitialAssignTeamEvent(Player player, Team team) {
        super(player);
        this.team = team;
    }

    public PlayerInitialAssignTeamEvent(){

    }

    public Team getTeam() {
        return team;
    }

    /** java-bean */
    public void setTeam(Team team) {
        this.team = team;
    }

}
