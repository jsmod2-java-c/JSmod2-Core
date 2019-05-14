package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author Kevinj
 */

public class WarheadEvent extends Event {
    public float TimeLeft;
    private Player player;
    public Player Activator;
    public boolean Cancel;

    public float getTimeLeft() {
        return TimeLeft;
    }

    public void setTimeLeft(float timeLeft) {
        TimeLeft = timeLeft;
    }

    public boolean isCancel() {
        return Cancel;
    }

    public void setCancel(boolean cancel) {
        Cancel = cancel;
    }

    public Player getActivator() {
        return Activator;
    }

    public WarheadEvent(Player player, float timeLeft){
        this.player = player;
        this.TimeLeft = timeLeft;
        this.Cancel = false;
    }
}
