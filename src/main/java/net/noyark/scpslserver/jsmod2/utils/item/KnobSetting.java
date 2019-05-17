package net.noyark.scpslserver.jsmod2.utils.item;

public enum KnobSetting {

    ROUGH(0),
    COARSE(1),
    ONE_TO_ONE(2),
    FINE(3),
    VERY_FINE(4);

    private int setting;

    KnobSetting(int setting){
        this.setting = setting;
    }

    public int getSetting() {
        return setting;
    }

    public void setSetting(int setting) {
        this.setting = setting;
    }
}
