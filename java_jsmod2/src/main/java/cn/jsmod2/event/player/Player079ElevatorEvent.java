/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.player;

import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.map.Elevator;
import cn.jsmod2.api.player.Player;

/**
 * @author kevinj
 */

public class Player079ElevatorEvent extends PlayerEvent {
    private Elevator elevator;
    private boolean allow;
    private float apDrain;

    public Elevator getElevator() {
        return elevator;
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
    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public Player079ElevatorEvent(Player player, Elevator elevator, boolean allow, float apDrain) {
        super(player);
        this.elevator = elevator;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079ElevatorEvent(){

    }
}
