package net.noyark.scpslserver.jsmod2.event.team;

import net.noyark.scpslserver.jsmod2.event.Event;

public class SetNTFUnitNameEvent extends Event {

    private String unit;

    public SetNTFUnitNameEvent(String unit) {
        this.unit = unit;
    }

    public SetNTFUnitNameEvent(){

    }
}
