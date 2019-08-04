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
import cn.jsmod2.core.math.Vector;

/**
 * @author kevinj
 */
public class PlayerPocketDimensionEnterEvent extends PlayerEvent implements IPlayerPocketDimensionEnterEvent{
    private float damage;

    private Vector lastPosition;

    private Vector targerPosision;

    private Player attacker = new Player("");

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setTargerPosision(Vector targerPosision) {
        this.targerPosision = targerPosision;
    }

    public float getDamage() {
        return damage;
    }

    public Vector getLastPosition() {
        return lastPosition;
    }

    public Vector getTargerPosision() {
        return targerPosision;
    }

    public Player getAttacker() {
        return attacker;
    }


    public PlayerPocketDimensionEnterEvent(Player player, float damage, Vector lastPosition, Vector targerPosision, Player attacker) {
        super(player);
        this.damage = damage;
        this.lastPosition = lastPosition;
        this.targerPosision = targerPosision;
        this.attacker = attacker;
    }

    public PlayerPocketDimensionEnterEvent(){

    }
}
