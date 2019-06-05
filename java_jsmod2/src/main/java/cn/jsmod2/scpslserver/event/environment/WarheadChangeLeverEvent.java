package cn.jsmod2.scpslserver.event.environment;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.event.Event;

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
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}
