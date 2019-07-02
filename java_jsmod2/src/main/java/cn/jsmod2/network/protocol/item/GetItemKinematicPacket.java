package cn.jsmod2.network.protocol.item;

public class GetItemKinematicPacket extends GetItemPacket{

    public GetItemKinematicPacket() {
        super(0x3d,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)(requester.with("field","kinematic").end(playerName).get().get());
    }
}
