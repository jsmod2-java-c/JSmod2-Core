package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Item;
import cn.jsmod2.scpslserver.entity.Player;

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
