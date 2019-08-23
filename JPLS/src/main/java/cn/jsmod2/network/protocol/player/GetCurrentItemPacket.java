package cn.jsmod2.network.protocol.player;

import cn.jsmod2.api.item.Item;

public class GetCurrentItemPacket extends GetPlayerPacket {


    public GetCurrentItemPacket() {
        super(193, Item.class);
    }

    @Override
    public Item send() {
        return (Item) requester.with("method","GetCurrentItem").get().get();
    }
}
