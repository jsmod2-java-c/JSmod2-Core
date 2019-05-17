package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class WarheadChangeLeverEvent extends Event {
    private Player player;
    private boolean allow;

    public Player getPlayer() {
        return player;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public WarheadChangeLeverEvent(Player player, boolean allow) {
        this.player = player;
        this.allow = allow;
    }

    public WarheadChangeLeverEvent(){

    }


    /** java-bean */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
