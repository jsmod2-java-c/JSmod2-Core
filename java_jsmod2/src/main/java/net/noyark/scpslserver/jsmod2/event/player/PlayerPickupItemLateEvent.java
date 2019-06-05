package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.entity.Item;
import net.noyark.scpslserver.jsmod2.entity.Player;

/**
 * @author kevinj
 */

public class PlayerPickupItemLateEvent extends PlayerEvent{
    private Item item;

    public PlayerPickupItemLateEvent(Player player, Item item) {
        super(player);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    /** java-bean */
    @UseForServerInit
    public void setItem(Item item) {
        this.item = item;
    }
}
