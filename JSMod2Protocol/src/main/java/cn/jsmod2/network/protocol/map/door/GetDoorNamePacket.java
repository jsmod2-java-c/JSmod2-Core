package cn.jsmod2.network.protocol.map.door;

public class GetDoorNamePacket extends GetDoorPacket {

    public static final int ID = 119;

    public GetDoorNamePacket(){
        super(ID,String.class);
    }

    @Override
    public String send() {
        return (String)requester.with("field","name").get().get();
    }
}
