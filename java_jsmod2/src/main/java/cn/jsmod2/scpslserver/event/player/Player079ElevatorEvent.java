package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Elevator;
import cn.jsmod2.scpslserver.entity.Player;

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
