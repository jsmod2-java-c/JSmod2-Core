package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.player.DamageType;

/**
 * @author kevinj
 */

public class PlayerDeathEvent extends PlayerEvent{
    private Player killer;

    private boolean SpawnRagdoll;

    private DamageType damageType;

    public void setSpawnRagdoll(boolean spawnRagdoll) {
        SpawnRagdoll = spawnRagdoll;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Player getKiller() {
        return killer;
    }

    public boolean isSpawnRagdoll() {
        return SpawnRagdoll;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setKiller(Player killer) {
        this.killer = killer;
    }

    public PlayerDeathEvent(Player player, Player killer, boolean spawnRagdoll, DamageType damageType) {
        super(player);
        this.killer = killer;
        SpawnRagdoll = spawnRagdoll;
        this.damageType = damageType;
    }

    public PlayerDeathEvent(){

    }

}
