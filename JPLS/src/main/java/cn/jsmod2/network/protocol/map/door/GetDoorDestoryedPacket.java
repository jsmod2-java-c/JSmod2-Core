package cn.jsmod2.network.protocol.map.door;

public class GetDoorDestoryedPacket extends GetDoorPacket {

    public static final int ID = 110;

    public GetDoorDestoryedPacket() {
        super(ID, Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean) requester.with("field","destoryed").get().get();
    }
}
