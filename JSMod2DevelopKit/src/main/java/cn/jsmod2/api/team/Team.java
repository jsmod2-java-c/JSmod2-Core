/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.team;

public enum Team {

    NONE(-1),
    SCP(0),
    NINETAILFOX(1),
    CHAOS_INSURGENCY(2),
    SCIENTIST(3),
    CLASSD(4),
    SPECTATOR(5),
    TUTORIAL(6);

    private int team;

    Team(int i){
        this.team = i;
    }

    public int getTeam() {
        return team;
    }
}
