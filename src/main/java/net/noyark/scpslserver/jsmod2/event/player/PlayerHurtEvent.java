package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.utils.player.DamageType;

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
