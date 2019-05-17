package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Door;
import net.noyark.scpslserver.jsmod2.entity.Player;
/**
 * @author kevinj
 */
public class PlayerSpawnEvent extends PlayerEvent {
    private Door door;
    private boolean allow;
    private boolean destroy;

    public PlayerSpawnEvent(Player player, Door door) {
        super(player);
        this.door = door;
    }

    public Door getDoor() {
        return door;
    }

    /** java-bean */
    public void setDoor(Door door) {
        this.door = door;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public PlayerSpawnEvent(){

    }

}
