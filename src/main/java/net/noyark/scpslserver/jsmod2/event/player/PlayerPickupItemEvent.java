package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerPickupItemEvent extends PlayerItemEvent {
    public PlayerPickupItemEvent(Player player, Item item, ItemType changeTo, boolean allow) {
        super(player, item, changeTo, allow);
    }
}
