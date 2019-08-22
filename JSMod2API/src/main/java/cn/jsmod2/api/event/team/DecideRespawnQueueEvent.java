/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.team;

import cn.jsmod2.api.team.Team;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

import java.util.List;

public class DecideRespawnQueueEvent extends Event implements IDecideRespawnQueueEvent{

    private Team[] teams;



    public Team[] getTeams() {
        teams = (Team[]) PacketSender.sendEventGetPacket(playerName,"Teams",Team.class, List.class, GetTypes.GET_ARRAY).toArray();
        return teams;
    }

    public void setTeams(Team[] teams){
        PacketSender.sendEventSetPacket(playerName,"Teams",teams);
        this.teams = teams;
    }


}
