package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

/**
 * @author kevinj
 */

public class Scp096PanicEvent extends PlayerEvent {
    private boolean allow;
    private float panicTime;

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public float getPanicTime() {
        return panicTime;
    }

    public void setPanicTime(float panicTime) {
        this.panicTime = panicTime;
    }

    public Scp096PanicEvent(Player player, boolean allow, float panicTime) {
        super(player);
        this.allow = allow;
        this.panicTime = panicTime;
    }

    public Scp096PanicEvent(){

    }
}
