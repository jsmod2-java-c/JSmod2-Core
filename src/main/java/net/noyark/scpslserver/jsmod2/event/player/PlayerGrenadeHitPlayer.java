package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerGrenadeHitPlayer extends PlayerEvent {
    private Player victim;

    public Player getVictim() {
        return victim;
    }

    public PlayerGrenadeHitPlayer(Player player, Player victim) {
        super(player);
        this.victim = victim;
    }
}
