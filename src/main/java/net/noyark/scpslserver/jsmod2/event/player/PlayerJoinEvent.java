package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerJoinEvent extends PlayerEvent{
    public PlayerJoinEvent(Player player) {
        super(player);
    }
}
