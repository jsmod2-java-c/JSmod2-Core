package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public abstract class WarheadEvent extends Event {
    private float timeLeft;
    private Player player;
    private Player activator;
    private boolean cancel;

    public float getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(float timeLeft) {
        timeLeft = timeLeft;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        cancel = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    /** java-bean */
    public void setActivator(Player activator) {
        this.activator = activator;
    }

    public Player getActivator() {
        return activator;
    }

    public WarheadEvent(Player player, float timeLeft){
        this.player = player;
        this.timeLeft = timeLeft;
        this.cancel = false;
    }

    public WarheadEvent(){

    }

    /** java-bean */
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}
