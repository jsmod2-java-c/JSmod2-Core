package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerPocketDimensionEnterEvent extends PlayerEvent {
    private float damage;

    private Vector lastPosition;

    private Vector targerPosision;

    private Player attacker;

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
}
