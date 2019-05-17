package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.math.Vector;

/**
 * @author kevinj
 */

public class Player079CameraTeleportEvent extends PlayerEvent {
    private Vector camera;
    private boolean allow;
    private float apDrain;

    public Player079CameraTeleportEvent(Player player, Vector camera, boolean allow, float apDrain) {
        super(player);
        this.camera = camera;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079CameraTeleportEvent(){

    }

    public Vector getCamera() {
        return camera;
    }

    /** java-bean */
    public void setCamera(Vector camera) {
        this.camera = camera;
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


}
