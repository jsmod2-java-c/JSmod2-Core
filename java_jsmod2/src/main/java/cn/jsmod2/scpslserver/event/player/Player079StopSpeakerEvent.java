package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.api.Room;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class Player079StopSpeakerEvent extends PlayerEvent {
    private Room room;
    private boolean allow;

    public Room getRoom() {
        return room;
    }

    public boolean isAllow() {
        return allow;
    }

    /** java-bean */
    @UseForServerInit
    public void setRoom(Room room) {
        this.room = room;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public Player079StopSpeakerEvent(Player player, Room room, boolean allow) {
        super(player);
        this.room = room;
        this.allow = allow;
    }

    public Player079StopSpeakerEvent(){

    }
}
