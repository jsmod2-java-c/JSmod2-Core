package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Item;
import cn.jsmod2.scpslserver.utils.item.ItemType;
import cn.jsmod2.scpslserver.entity.Player;

public class PlayerPickupItemEvent extends PlayerItemEvent {
    public PlayerPickupItemEvent(Player player, Item item, ItemType changeTo, boolean allow) {
        super(player, item, changeTo, allow);
    }
    public PlayerPickupItemEvent(){

    }
}
