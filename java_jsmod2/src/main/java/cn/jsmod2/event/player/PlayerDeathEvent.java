/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.player;

import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.player.DamageType;
import cn.jsmod2.api.player.Player;

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

    /** java-bean */
    @UseForServerInit
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
