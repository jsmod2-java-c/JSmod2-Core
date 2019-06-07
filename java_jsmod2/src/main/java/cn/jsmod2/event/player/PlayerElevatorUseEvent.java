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
import cn.jsmod2.api.player.Player;
import cn.jsmod2.math.Vector;
import cn.jsmod2.api.map.Elevator;

/**
 * @author kevinj
 */

public class PlayerElevatorUseEvent extends PlayerEvent {
    private Elevator Elevator;
    private Vector ElevatorPosition;
    private boolean AllowUse;

    public Elevator getElevator() {
        return Elevator;
    }

    public Vector getElevatorPosition() {
        return ElevatorPosition;
    }

    public boolean getAllowUse() {
        return AllowUse;
    }

    public PlayerElevatorUseEvent(Player player, Elevator elevator, Vector elevatorPosition, boolean allowUse) {
        super(player);
        Elevator = elevator;
        ElevatorPosition = elevatorPosition;
        AllowUse = allowUse;
    }

    public PlayerElevatorUseEvent(){

    }

    public void setAllowUse(boolean allowUse) {
        AllowUse = allowUse;
    }
    /** java-bean */
    @UseForServerInit
    public void setElevator(Elevator elevator) {
        Elevator = elevator;
    }
    /** java-bean */
    @UseForServerInit
    public void setElevatorPosition(Vector elevatorPosition) {
        ElevatorPosition = elevatorPosition;
    }

    public boolean isAllowUse() {
        return AllowUse;
    }
}
