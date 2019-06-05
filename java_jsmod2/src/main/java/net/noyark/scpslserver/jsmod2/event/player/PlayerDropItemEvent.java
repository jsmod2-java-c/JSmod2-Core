package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Item;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.item.ItemType;

/**
 * @author kevinj
 */

public class PlayerDropItemEvent extends PlayerItemEvent {
    public PlayerDropItemEvent(Player player, Item item, ItemType changeTo, boolean allow) {
        super(player, item, changeTo, allow);
    }

    public PlayerDropItemEvent(){

    }
}
