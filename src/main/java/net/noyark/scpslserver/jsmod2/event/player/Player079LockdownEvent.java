package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Room;

/**
 * @author kevinj
 */

public class Player079LockdownEvent extends PlayerEvent {
    private Room room;
    private boolean allow;
    private float apDrain;

    public Player079LockdownEvent(Player player, Room room, boolean allow, float apDrain) {
        super(player);
        this.room = room;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079LockdownEvent(){

    }


    public Room getRoom() {
        return room;
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
    public void setRoom(Room room) {
        this.room = room;
    }
}
