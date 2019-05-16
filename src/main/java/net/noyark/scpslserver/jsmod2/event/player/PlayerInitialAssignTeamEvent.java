package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerInitialAssignTeamEvent extends PlayerEvent {
    private Team team;

    public PlayerInitialAssignTeamEvent(Player player, Team team) {
        super(player);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
