package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerThrowGrenade extends PlayerEvent {
    private ItemType GrenadeType;
    private Vector direction;
    private boolean slowThrow;

    public PlayerThrowGrenade(Player player, ItemType grenadeType, Vector direction, boolean slowThrow) {
        super(player);
        GrenadeType = grenadeType;
        this.direction = direction;
        this.slowThrow = slowThrow;
    }
}
