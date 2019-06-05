package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;

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
