package cn.jsmod2.network.protocol.map.door;

public class GetDoorDontOpenOnWarheadPacket extends GetDoorPacket {


    public static final int ID = 112;

    public GetDoorDontOpenOnWarheadPacket() {
        super(112, Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean) requester.with("field","dontOpenOnWarhead").get().get();
    }
}
