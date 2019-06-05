package net.noyark.scpslserver.jsmod2.event.team;

import net.noyark.scpslserver.jsmod2.event.Event;

public class SetNTFUnitNameEvent extends Event {

    private String unit;

    public SetNTFUnitNameEvent(String unit) {
        this.unit = unit;
    }

    public SetNTFUnitNameEvent(){

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
