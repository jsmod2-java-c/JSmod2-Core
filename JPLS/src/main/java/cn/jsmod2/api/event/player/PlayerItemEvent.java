/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.item.IItem;
import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.network.PacketSender;

public abstract class PlayerItemEvent extends PlayerEvent {
    private IItem item = new Item();

    private ItemType changeTo;

    private boolean allow;

    public PlayerItemEvent(Player player, Item item, ItemType changeTo, boolean allow) {
        super(player);
        this.item = item;
        this.changeTo = changeTo;
        this.allow = allow;
    }

    public PlayerItemEvent(){

    }

    public IItem getItem() {
        return item;
    }

    public void setItem(IItem item) {
        PacketSender.sendEventSetPacket(playerName,"Item",item);
        this.item = item;
    }

    public ItemType getChangeTo() {
        changeTo = PacketSender.sendEventGetPacket(playerName,"ChangeTo",ItemType.class);
        return changeTo;
    }

    public void setChangeTo(ItemType changeTo) {
        PacketSender.sendEventSetPacket(playerName,"ChangeTo",changeTo);
        this.changeTo = changeTo;
    }

    public boolean isAllow() {
        allow = PacketSender.sendEventGetPacket(playerName,"Allow",Boolean.class);
        return allow;
    }

    public void setAllow(boolean allow) {
        PacketSender.sendEventSetPacket(playerName,"Allow",allow);
        this.allow = allow;
    }


}
