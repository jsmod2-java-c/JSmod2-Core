package cn.jsmod2.network.protocol.player;

import cn.jsmod2.api.item.Item;
import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.core.protocol.GetPacket;

public class GiveItemPacket extends GetPacket {

    public static final int ID = 191;

    public ItemType type;

    public GiveItemPacket() {
        super(ID, Item.class);
    }

    @Override
    public Object send() {
        return requester.with("item",type).get().get();
    }
}
