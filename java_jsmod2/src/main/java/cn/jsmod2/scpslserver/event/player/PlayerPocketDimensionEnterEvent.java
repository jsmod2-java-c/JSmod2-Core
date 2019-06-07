package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author kevinj
 */
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

    /** java-bean */
    @UseForServerInit
    public void setLastPosition(Vector lastPosition) {
        this.lastPosition = lastPosition;
    }

    /** java-bean */
    @UseForServerInit
    public void setAttacker(Player attacker) {
        this.attacker = attacker;
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
