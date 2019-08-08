package cn.jsmod2.network.protocol.map.door;

public class GetDoorLockedPacket extends GetDoorPacket {

    public static final int ID = 116;

    public GetDoorLockedPacket(){
        super(ID,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)requester.with("field","door").get().get();
    }
}