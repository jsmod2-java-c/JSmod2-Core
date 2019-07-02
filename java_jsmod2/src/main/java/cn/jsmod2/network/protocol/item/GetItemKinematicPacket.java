package cn.jsmod2.network.protocol.item;

public class GetItemKinematicPacket extends GetItemPacket{

    public static final int ID = 0x5d;

    public GetItemKinematicPacket() {
        super(0x5d,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)(requester.with("field","kinematic").end(playerName).get().get());
    }
}
