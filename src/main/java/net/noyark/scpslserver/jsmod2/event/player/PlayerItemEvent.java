package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerItemEvent extends PlayerEvent {
    private Item item;

    private ItemType changeTo;

    private boolean allow;

    public PlayerItemEvent(Player player, Item item, ItemType changeTo, boolean allow) {
        super(player);
        this.item = item;
        this.changeTo = changeTo;
        this.allow = allow;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemType getChangeTo() {
        return changeTo;
    }

    public void setChangeTo(ItemType changeTo) {
        this.changeTo = changeTo;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
