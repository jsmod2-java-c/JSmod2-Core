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
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */

public class PlayerHandcuffedEvent extends PlayerEvent implements IPlayerHandcuffedEvent{
    private boolean handcuffed;

    private Player owner = new Player("");

    public boolean isHandcuffed() {
        handcuffed = PacketSender.sendEventGetPacket(playerName,"Handcuffed",Boolean.class);
        return handcuffed;
    }

    public void setHandcuffed(boolean handcuffed) {
        PacketSender.sendEventSetPacket(playerName,"Handcuffed",handcuffed);
        this.handcuffed = handcuffed;
    }

    public Player getOwner() {
        return owner;
    }




    public PlayerHandcuffedEvent(Player player, boolean handcuffed, Player owner) {
        super(player);
        this.handcuffed = handcuffed;
        this.owner = owner;
    }

    public PlayerHandcuffedEvent(){

    }
}
