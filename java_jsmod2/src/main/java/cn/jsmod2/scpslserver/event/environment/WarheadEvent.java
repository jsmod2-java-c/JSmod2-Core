/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.event.environment;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.entity.Player;
import cn.jsmod2.scpslserver.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public abstract class WarheadEvent extends Event {
    private float timeLeft;
    private Player player;
    private Player activator;
    private boolean cancel;

    public float getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(float timeLeft) {
        timeLeft = timeLeft;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        cancel = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    /** java-bean */
    public void setActivator(Player activator) {
        this.activator = activator;
    }

    public Player getActivator() {
        return activator;
    }

    public WarheadEvent(Player player, float timeLeft){
        this.player = player;
        this.timeLeft = timeLeft;
        this.cancel = false;
    }

    public WarheadEvent(){

    }

    /** java-bean */
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}
