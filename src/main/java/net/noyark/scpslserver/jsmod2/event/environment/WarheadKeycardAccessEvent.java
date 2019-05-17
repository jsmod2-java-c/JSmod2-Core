package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class WarheadKeycardAccessEvent extends Event {
    public Player player;
    public boolean allow;

    public Player getPlayer() {
        return player;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public WarheadKeycardAccessEvent(Player player, boolean allow) {
        this.player = player;
        allow = allow;
    }

    public WarheadKeycardAccessEvent(){

    }

    /** java-bean */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
