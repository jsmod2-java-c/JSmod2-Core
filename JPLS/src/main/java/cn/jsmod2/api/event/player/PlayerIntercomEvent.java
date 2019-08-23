/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
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

public class PlayerIntercomEvent extends PlayerEvent implements IPlayerIntercomEvent{
    private float speechTime;
    private float cooldownTime;

    public float getSpeechTime() {
        speechTime = PacketSender.sendEventGetPacket(playerName,"SpeechTime",Float.class);
        return speechTime;
    }

    public void setSpeechTime(float speechTime) {
        PacketSender.sendEventSetPacket(playerName,"SpeechTime",speechTime);
        this.speechTime = speechTime;
    }

    public float getCooldownTime() {
        cooldownTime = PacketSender.sendEventGetPacket(playerName,"CooldownTime",Float.class);
        return cooldownTime;
    }

    public void setCooldownTime(float cooldownTime) {
        PacketSender.sendEventSetPacket(playerName,"CooldownTime",cooldownTime);
        this.cooldownTime = cooldownTime;
    }

    public PlayerIntercomEvent(Player player, float speechTime, float cooldownTime) {
        super(player);
        this.speechTime = speechTime;
        this.cooldownTime = cooldownTime;
    }

    public PlayerIntercomEvent(){

    }
}
