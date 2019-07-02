package cn.jsmod2.network.protocol.item;


public class GetComponentPacket extends GetItemPacket {

    public GetComponentPacket() {
        super(0x5c, String.class);
    }

    @Override
    public Object send() {
        return requester
                .with("field","component")
                .end(playerName)
                .get().get();
    }
}
