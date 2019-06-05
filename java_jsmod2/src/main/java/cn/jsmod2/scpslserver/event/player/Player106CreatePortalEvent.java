package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author kevinj
 */

public class Player106CreatePortalEvent extends PlayerEvent {
    private Vector position;

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Player106CreatePortalEvent(Player player, Vector position) {
        super(player);
        this.position = position;
    }

    public Player106CreatePortalEvent(){

    }
}
