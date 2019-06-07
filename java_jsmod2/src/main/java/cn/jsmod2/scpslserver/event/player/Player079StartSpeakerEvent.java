/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.api.Room;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class Player079StartSpeakerEvent extends PlayerEvent {
    private Room room;
    private boolean allow;
    private float apDrain;

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
    @UseForServerInit
    public void setRoom(Room room) {
        this.room = room;
    }

    public Player079StartSpeakerEvent(Player player, Room room, boolean allow, float apDrain) {
        super(player);
        this.room = room;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079StartSpeakerEvent(){

    }
}
