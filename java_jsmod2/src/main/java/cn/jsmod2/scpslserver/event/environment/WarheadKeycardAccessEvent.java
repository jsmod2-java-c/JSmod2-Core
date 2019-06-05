package cn.jsmod2.scpslserver.event.environment;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.event.Event;

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
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}
