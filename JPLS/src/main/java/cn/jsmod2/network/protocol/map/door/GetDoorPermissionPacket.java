package cn.jsmod2.network.protocol.map.door;

public class GetDoorPermissionPacket extends GetDoorPacket {

    public static final int ID = 120;

    public GetDoorPermissionPacket() {
        super(ID,String.class);
    }

    @Override
    public String send() {
        return (String) requester.with("field","permission").get().get();
    }
}
