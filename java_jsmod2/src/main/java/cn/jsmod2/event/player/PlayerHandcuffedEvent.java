/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.player;

import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.player.Player;

/**
 * @author kevinj
 */

public class PlayerHandcuffedEvent extends PlayerEvent {
    private boolean Handcuffed;

    private Player Owner;

    public boolean isHandcuffed() {
        return Handcuffed;
    }

    public void setHandcuffed(boolean handcuffed) {
        Handcuffed = handcuffed;
    }

    public Player getOwner() {
        return Owner;
    }

    /** java-bean */
    @UseForServerInit
    public void setOwner(Player owner) {
        Owner = owner;
    }



    public PlayerHandcuffedEvent(Player player, boolean handcuffed, Player owner) {
        super(player);
        Handcuffed = handcuffed;
        Owner = owner;
    }

    public PlayerHandcuffedEvent(){

    }
}