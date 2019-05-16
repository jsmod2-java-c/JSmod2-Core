package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

/**
 * @author kevinj
 */

public class PlayerLureEvent extends PlayerEvent {
    private boolean allowContain;

    public PlayerLureEvent(Player player, boolean allowContain) {
        super(player);
        this.allowContain = allowContain;
    }

    public PlayerLureEvent(){

    }

    public boolean isAllowContain() {
        return allowContain;
    }

    public void setAllowContain(boolean allowContain) {
        this.allowContain = allowContain;
    }
}
