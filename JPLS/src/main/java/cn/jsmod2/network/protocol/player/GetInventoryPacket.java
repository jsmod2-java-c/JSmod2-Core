package cn.jsmod2.network.protocol.player;


import cn.jsmod2.api.item.Item;

import java.util.List;

public class GetInventoryPacket extends GetPlayerPacket {

    public GetInventoryPacket() {
        super(192, Item.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> send() {
        return requester.with("do","GetInventory").get().getProtocolArray(false);
    }
}
