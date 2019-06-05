package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerGrenadeHitPlayer extends PlayerEvent {
    private Player victim;

    public Player getVictim() {
        return victim;
    }

    public PlayerGrenadeHitPlayer(Player player, Player victim) {
        super(player);
        this.victim = victim;
    }

    public PlayerGrenadeHitPlayer(){

    }

    /** java-bean */
    @UseForServerInit
    public void setVictim(Player victim) {
        this.victim = victim;
    }


}
