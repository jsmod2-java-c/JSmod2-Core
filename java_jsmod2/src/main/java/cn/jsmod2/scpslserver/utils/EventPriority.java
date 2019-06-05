package cn.jsmod2.scpslserver.utils;

/**
 * Event's priority
 *
 * use event:1->6
 * 1 lowest
 * 2 low
 * 3 normal
 * 4 high
 * 5 highest
 * 6 monitor
 * @author magiclu550 #(code) jsmod2
 */

public enum EventPriority {

    LOWEST(0),

    LOW(1),

    NORMAL(2),

    HIGH(3),

    HIGHEST(4),
    MONITOR(5);

    private int slot;

    EventPriority(int slot){
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
