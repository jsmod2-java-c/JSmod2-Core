package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

/**
 * @author kevinj
 */

public class Player079UnlockDoorsEvent extends PlayerEvent {
    private boolean allow;

    public Player079UnlockDoorsEvent(Player player, boolean allow) {
        super(player);
        this.allow = allow;
    }

    public Player079UnlockDoorsEvent(){

    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }


}
