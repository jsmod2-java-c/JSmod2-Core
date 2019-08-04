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

/**
 * @author kevinj
 */

public class Scp096PanicEvent extends PlayerEvent implements IScp096PanicEvent{
    private boolean allow;
    private float panicTime;

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public float getPanicTime() {
        return panicTime;
    }

    public void setPanicTime(float panicTime) {
        this.panicTime = panicTime;
    }

    public Scp096PanicEvent(Player player, boolean allow, float panicTime) {
        super(player);
        this.allow = allow;
        this.panicTime = panicTime;
    }

    public Scp096PanicEvent(){

    }
}
