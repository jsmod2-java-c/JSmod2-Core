/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.math.Vector;

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
