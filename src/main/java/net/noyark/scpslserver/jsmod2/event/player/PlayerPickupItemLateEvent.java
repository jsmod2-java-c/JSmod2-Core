package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerPickupItemLateEvent extends PlayerEvent{
    public Item item;

    public PlayerPickupItemLateEvent(Player player, Item item) {
        super(player);
        this.item = item;
    }
}
