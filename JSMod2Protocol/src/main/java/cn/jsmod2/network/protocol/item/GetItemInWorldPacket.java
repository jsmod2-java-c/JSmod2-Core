package cn.jsmod2.network.protocol.item;

//未收录
public class GetItemInWorldPacket extends GetItemPacket {

    public static final int ID = 0x67;

    public GetItemInWorldPacket() {
        super(ID, Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)(requester.with("field","inWorld").get().get());
    }
}
