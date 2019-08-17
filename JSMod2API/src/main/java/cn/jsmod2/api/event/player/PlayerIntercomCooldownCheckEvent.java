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

public class PlayerIntercomCooldownCheckEvent extends PlayerEvent implements IPlayerIntercomCooldownCheckEvent{
    private float currentCooldown;

    public PlayerIntercomCooldownCheckEvent(Player player, float currentCooldown) {
        super(player);
        this.currentCooldown = currentCooldown;
    }

    public PlayerIntercomCooldownCheckEvent(){

    }

    public float getCurrentCooldown() {
        currentCooldown = PacketSender.sendEventGetPacket(playerName,"CurrentCooldown",Float.class);
        return currentCooldown;
    }

    public void setCurrentCooldown(float currentCooldown) {
        PacketSender.sendEventSetPacket(playerName,"CurrentCoolDown",currentCooldown);
        this.currentCooldown = currentCooldown;
    }
}
