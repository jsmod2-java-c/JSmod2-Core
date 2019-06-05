package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author kevinj
 */

public class Player106TeleportEvent extends PlayerEvent {
    private Vector position;

    public Vector getPosition() {
        return position;
    }

    /** java-bean */
    @UseForServerInit
    public void setPosition(Vector position) {
        this.position = position;
    }

    public Player106TeleportEvent(Player player, Vector position) {
        super(player);
        this.position = position;
    }

    public Player106TeleportEvent(){

    }
}
