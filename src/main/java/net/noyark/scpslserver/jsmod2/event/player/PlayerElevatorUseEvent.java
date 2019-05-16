package net.noyark.scpslserver.jsmod2.event.player;


import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerElevatorUseEvent extends PlayerEvent {
    private Elevator Elevator;
    private Vector ElevatorPosition;
    private bool AllowUse;

    public Elevator getElevator() {
        return Elevator;
    }

    public Vector getElevatorPosition() {
        return ElevatorPosition;
    }

    public bool getAllowUse() {
        return AllowUse;
    }

    public PlayerElevatorUseEvent(Player player, Elevator elevator, Vector elevatorPosition, bool allowUse) {
        super(player);
        Elevator = elevator;
        ElevatorPosition = elevatorPosition;
        AllowUse = allowUse;
    }

    public void setAllowUse(bool allowUse) {
        AllowUse = allowUse;
    }
}
