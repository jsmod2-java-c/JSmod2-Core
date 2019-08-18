/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.GrenadeType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */
public class PlayerThrowGrenade extends PlayerEvent implements IPlayerThrowGrenade{

    private GrenadeType grenadeType;
    private Vector direction;
    private boolean slowThrow;

    public PlayerThrowGrenade(Player player, GrenadeType grenadeType, Vector direction, boolean slowThrow) {
        super(player);
        this.grenadeType = grenadeType;
        this.direction = direction;
        this.slowThrow = slowThrow;
    }

    public PlayerThrowGrenade(){

    }

    public GrenadeType getGrenadeType() {
        grenadeType = PacketSender.sendEventGetPacket(playerName,"GrenadeType",GrenadeType.class);
        return grenadeType;
    }


    public Vector getDirection() {
        direction = PacketSender.sendEventGetPacket(playerName,"Direction",Vector.class);
        return direction;
    }


    public boolean isSlowThrow() {
        slowThrow = PacketSender.sendEventGetPacket(playerName,"SlowThrow",Boolean.class);
        return slowThrow;
    }



}
