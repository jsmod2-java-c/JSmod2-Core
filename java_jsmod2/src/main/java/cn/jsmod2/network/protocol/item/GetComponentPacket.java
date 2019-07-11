package cn.jsmod2.network.protocol.item;


public class GetComponentPacket extends GetItemPacket {

    public static final int ID = 0x5c;

    public GetComponentPacket() {
        super(0x5c, String.class);
    }

    @Override
    public Object send() {
        return requester
                .with("field","component")
                .with("player",playerName)
                .get().get();
    }
}
