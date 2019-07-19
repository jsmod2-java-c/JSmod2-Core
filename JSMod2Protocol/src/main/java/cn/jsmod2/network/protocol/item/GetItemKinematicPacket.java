package cn.jsmod2.network.protocol.item;

public class GetItemKinematicPacket extends GetItemPacket{

    public static final int ID = 0x5d;

    public GetItemKinematicPacket() {
        super(ID,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)(requester.with("field","kinematic")
                .with("player",playerName).get().get());
    }
}
