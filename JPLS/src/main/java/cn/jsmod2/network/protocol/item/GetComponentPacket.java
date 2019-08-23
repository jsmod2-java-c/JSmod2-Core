package cn.jsmod2.network.protocol.item;


import cn.jsmod2.api.item.Item;

public class GetComponentPacket extends GetItemPacket {

    public static final int ID = 0x5c;

    public GetComponentPacket() {
        super(ID, Item.class);
    }

    @Override
    public Object send() {
        return requester
                .with("field","component")
                .get().get();
    }
}
