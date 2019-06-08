/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.player;

import cn.jsmod2.api.team.TeamRole;

public interface IPlayer {

    TeamRole getTeamRole();

    void setTeamRole(TeamRole teamRole);


    String getIpAddress();


    int getPlayerId();


    String getStreamId();


    RadioStatus getRadioStatus();

    void setRadioStatus(RadioStatus radioStatus);


    boolean isOverwatchMode();


    boolean isDoNotTrack();

    Scp079Data getScp079Data();

    void kill(DamageType type);

    void kill();

    int getHealth();

    void addHealth(int amount);

    void personalBroadcast(int duration, String message, boolean isMonoSpaced);

}
