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
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.event.Event;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class WarheadKeycardAccessEvent extends Event {
    public Player player;
    public boolean allow;

    public Player getPlayer() {
        return player;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public WarheadKeycardAccessEvent(Player player, boolean allow) {
        this.player = player;
        allow = allow;
    }

    public WarheadKeycardAccessEvent(){

    }

    /** java-bean */
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}
