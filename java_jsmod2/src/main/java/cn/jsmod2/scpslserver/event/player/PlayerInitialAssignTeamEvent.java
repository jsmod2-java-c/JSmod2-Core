package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.team.Team;
import cn.jsmod2.scpslserver.entity.Player;

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
    @UseForServerInit
    public void setTeam(Team team) {
        this.team = team;
    }

}
