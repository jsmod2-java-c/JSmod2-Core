package cn.jsmod2.scpslserver.event.team;

import cn.jsmod2.scpslserver.event.Event;

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
