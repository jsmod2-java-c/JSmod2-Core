/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Item;
import cn.jsmod2.scpslserver.utils.item.ItemType;
import cn.jsmod2.scpslserver.entity.Player;

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
