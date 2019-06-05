package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Door;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class Player079DoorEvent extends PlayerEvent {
    private Door door;
    private boolean allow;
    private float apDrain;

    public Player079DoorEvent(Player player, Door door, boolean allow, float apDrain) {
        super(player);
        this.door = door;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079DoorEvent(){

    }

    public Door getDoor() {
        return door;
    }

    public boolean isAllow() {
        return allow;
    }



    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public float getApDrain() {
        return apDrain;
    }

    public void setApDrain(float apDrain) {
        this.apDrain = apDrain;
    }

    /** java-bean */
    @UseForServerInit
    public void setDoor(Door door) {
        this.door = door;
    }
}
