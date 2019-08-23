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
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */
public class PlayerPocketDimensionEnterEvent extends PlayerEvent implements IPlayerPocketDimensionEnterEvent{
    private float damage;

    private Vector lastPosition;

    private Vector targetPosition;

    private Player attacker = new Player("");

    public void setDamage(float damage) {
        PacketSender.sendEventSetPacket(playerName,"Damage",damage);
        this.damage = damage;
    }

    public void setTargetPosition(Vector targetPosition) {
        PacketSender.sendEventSetPacket(playerName,"TargetPosition",targetPosition);
        this.targetPosition = targetPosition;
    }

    public float getDamage() {
        damage = PacketSender.sendEventGetPacket(playerName,"Damage",Float.class);
        return damage;
    }

    public Vector getLastPosition() {
        lastPosition = PacketSender.sendEventGetPacket(playerName,"LastPosition",Vector.class);
        return lastPosition;
    }

    public Vector getTargetPosition() {
        targetPosition = PacketSender.sendEventGetPacket(playerName,"TargetPosition",Vector.class);
        return targetPosition;
    }

    public Player getAttacker() {
        return attacker;
    }


    public PlayerPocketDimensionEnterEvent(Player player, float damage, Vector lastPosition, Vector targetPosition, Player attacker) {
        super(player);
        this.damage = damage;
        this.lastPosition = lastPosition;
        this.targetPosition = targetPosition;
        this.attacker = attacker;
    }

    public PlayerPocketDimensionEnterEvent(){

    }
}
