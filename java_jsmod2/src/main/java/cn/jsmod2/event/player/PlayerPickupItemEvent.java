/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.player;

import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.Player;

public class PlayerPickupItemEvent extends PlayerItemEvent {
    public PlayerPickupItemEvent(Player player, Item item, ItemType changeTo, boolean allow) {
        super(player, item, changeTo, allow);
    }
    public PlayerPickupItemEvent(){

    }
}
