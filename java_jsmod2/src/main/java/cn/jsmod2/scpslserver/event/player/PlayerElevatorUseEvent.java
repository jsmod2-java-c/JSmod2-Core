package cn.jsmod2.scpslserver.event.player;


import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Elevator;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author kevinj
 */

public class PlayerElevatorUseEvent extends PlayerEvent {
    private cn.jsmod2.scpslserver.entity.Elevator Elevator;
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
    public void setElevator(cn.jsmod2.scpslserver.entity.Elevator elevator) {
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
