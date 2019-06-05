package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class SummonVehicleEvent extends Event {
    private boolean IsCI;

    private boolean allowSummon;

    public boolean isCI() {
        return IsCI;
    }

    public void setCI(boolean CI) {
        IsCI = CI;
    }

    public boolean isAllowSummon() {
        return allowSummon;
    }

    public void setAllowSummon(boolean allowSummon) {
        this.allowSummon = allowSummon;
    }

    public SummonVehicleEvent(boolean isCI, boolean allowSummon) {
        IsCI = isCI;
        this.allowSummon = allowSummon;
    }

    public SummonVehicleEvent(){

    }

}
