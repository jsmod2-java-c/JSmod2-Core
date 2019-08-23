/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.player;

public enum ExperienceType {

    KILL_ASSIST_CLASSD(0),
    KILL_ASSIST_CHAOS_INSURGENCY(1),
    KILL_ASSIST_NINETAILFOX(2),
    KILL_ASSIST_SCIENTIST(3),
    KILL_ASSIST_SCP(4),
    KILL_ASSIST_OTHER(5),
    USE_DOOR(6),
    USE_LOCKDOWN(7),
    USE_TESLAGATE(8),
    USE_ELEVATOR(9),
    CHEAT(10);

    private int type;

    public int getType(){
        return type;
    }

    ExperienceType(int type){
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
