package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerSpawnEvent extends PlayerEvent {
    private Door door;
    private boolean allow;
    private boolean destroy;

    public PlayerSpawnEvent(Player player, Door door) {
        super(player);
        this.door = door;
    }

}
