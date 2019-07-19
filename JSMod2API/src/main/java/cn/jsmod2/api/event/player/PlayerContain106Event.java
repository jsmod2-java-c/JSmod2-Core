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

/**
 * @author kevinj
 */

public class PlayerContain106Event extends PlayerEvent {
    private Player[] scp106s;
    private boolean activateContainment;

    public Player[] getScp106s() {
        return scp106s;
    }

    public boolean isActivateContainment() {
        return activateContainment;
    }

    public void setActivateContainment(boolean activateContainment) {
        this.activateContainment = activateContainment;
    }

    /** java-bean */
    @UseForServerInit
    public void setScp106s(Player[] scp106s) {
        this.scp106s = scp106s;
    }

    public PlayerContain106Event(Player player, Player[] scp106s, boolean activateContainment) {
        super(player);
        this.scp106s = scp106s;
        this.activateContainment = activateContainment;
    }

    public PlayerContain106Event(){

    }
}
