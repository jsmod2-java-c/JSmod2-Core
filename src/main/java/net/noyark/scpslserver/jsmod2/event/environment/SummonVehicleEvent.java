package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author Kevinj
 */

public class SummonVehicleEvent extends Event {
    public boolean IsCI;

    public boolean AllowSummon;

    public boolean isCI() {
        return IsCI;
    }

    public void setCI(boolean CI) {
        IsCI = CI;
    }

    public boolean isAllowSummon() {
        return AllowSummon;
    }

    public void setAllowSummon(boolean allowSummon) {
        AllowSummon = allowSummon;
    }

    public SummonVehicleEvent(boolean isCI, boolean allowSummon) {
        IsCI = isCI;
        AllowSummon = allowSummon;
    }


}
