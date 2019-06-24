/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.api.map.TeslaGate;
import cn.jsmod2.api.player.Player;


/**
 * @author kevinj
 */

public class Player079TeslaGateEvent extends PlayerEvent {
    private TeslaGate teslaGate;
    private boolean allow;
    private float apDrain;

    public Player079TeslaGateEvent(Player player, TeslaGate teslaGate, boolean allow, float apDrain) {
        super(player);
        this.teslaGate = teslaGate;
        this.allow = allow;
        this.apDrain = apDrain;
    }

    public Player079TeslaGateEvent(){

    }

    public TeslaGate getTeslaGate() {
        return teslaGate;
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
    public void setTeslaGate(TeslaGate teslaGate) {
        this.teslaGate = teslaGate;
    }
}
