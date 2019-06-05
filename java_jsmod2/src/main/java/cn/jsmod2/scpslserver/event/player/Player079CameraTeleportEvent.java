package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

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
    @UseForServerInit
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
