package net.noyark.scpslserver.jsmod2.utils.player;

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

}
