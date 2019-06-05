package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Elevator;
import cn.jsmod2.scpslserver.entity.Player;

import java.util.Vector;

/**
 * @author kevinj
 */

public class Player079ElevatorTeleportEvent extends PlayerEvent {
    private Vector camera;
    private Elevator elevator;
    private boolean allow;
    private float apDrain;

    public Vector getCamera() {
        return camera;
    }

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
    public void setCamera(Vector camera) {
        this.camera = camera;
    }


    /** java-bean */
    @UseForServerInit
    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public Player079ElevatorTeleportEvent(Player player, Vector camera, Elevator elevator, boolean allow, float apDrain) {
        super(player);
        this.camera = camera;
        this.elevator = elevator;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079ElevatorTeleportEvent(){

    }
}
