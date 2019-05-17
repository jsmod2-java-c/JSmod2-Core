package net.noyark.scpslserver.jsmod2.event.player;


import net.noyark.scpslserver.jsmod2.entity.Elevator;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.math.Vector;

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
    public void setElevator(net.noyark.scpslserver.jsmod2.entity.Elevator elevator) {
        Elevator = elevator;
    }
    /** java-bean */
    public void setElevatorPosition(Vector elevatorPosition) {
        ElevatorPosition = elevatorPosition;
    }

    public boolean isAllowUse() {
        return AllowUse;
    }
}
