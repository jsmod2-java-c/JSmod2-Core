package cn.jsmod2.network.protocol.item;

import cn.jsmod2.api.item.ItemType;
//未收录
public class GetItemTypePacket extends GetItemPacket{

    public static final int ID = 0x68;

    public GetItemTypePacket() {
        super(ID, ItemType.class);
    }

    @Override
    public ItemType send() {
        return (ItemType) (requester.with("field","itemType").get().get());
    }
}
