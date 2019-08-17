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
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */

public class PlayerLureEvent extends PlayerEvent implements IPlayerLureEvent{
    private boolean allowContain;

    public PlayerLureEvent(Player player, boolean allowContain) {
        super(player);
        this.allowContain = allowContain;
    }

    public PlayerLureEvent(){

    }

    public boolean isAllowContain() {
        allowContain = PacketSender.sendEventGetPacket(playerName,"AllowContain",Boolean.class);
        return allowContain;
    }

    public void setAllowContain(boolean allowContain) {
        PacketSender.sendEventSetPacket(playerName,"AllowContain",allowContain);
        this.allowContain = allowContain;
    }
}
