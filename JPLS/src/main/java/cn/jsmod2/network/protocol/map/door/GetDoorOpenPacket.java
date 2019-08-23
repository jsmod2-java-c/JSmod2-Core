package cn.jsmod2.network.protocol.map.door;

public class GetDoorOpenPacket extends GetDoorPacket{

    public static final int ID = 108;

    public GetDoorOpenPacket() {
        super(ID,Boolean.class);
    }

    @Override
    public  Boolean send() {
        return (Boolean) requester.with("field","open").get().get();
    }
}
