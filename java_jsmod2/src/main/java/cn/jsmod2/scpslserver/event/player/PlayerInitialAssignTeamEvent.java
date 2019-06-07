/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
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
