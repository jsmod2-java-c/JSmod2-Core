package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

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
    public void setVictim(Player victim) {
        this.victim = victim;
    }


}
