/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
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
