/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.utils.player.DamageType;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerHurtEvent extends PlayerEvent {

    private Player attacker;

    private float damage;

    private DamageType damageType;

    public PlayerHurtEvent(Player player, Player attacker, float damage, DamageType damageType) {
        super(player);
        this.attacker = attacker;
        this.damage = damage;
        this.damageType = damageType;
    }

    public PlayerHurtEvent(){

    }
    /** java-bean */
    public void setAttacker(Player attacker) {
        this.attacker = attacker;
    }

    public Player getAttacker() {
        return attacker;
    }

    public float getDamage() {
        return damage;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
