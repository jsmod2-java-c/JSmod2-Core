package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

/**
 * @author kevinj
 */

public class PlayerMakeNoiseEvent extends PlayerEvent {
    public PlayerMakeNoiseEvent(Player player) {
        super(player);
    }

    public PlayerMakeNoiseEvent(){

    }
}
