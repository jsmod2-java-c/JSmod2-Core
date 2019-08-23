package cn.jsmod2.network.protocol.map.door;

public class GetDoorBlockAfterWarheadDetonationPacket extends GetDoorPacket {

    public static final int ID = 114;

    public GetDoorBlockAfterWarheadDetonationPacket(){
        super(ID,Boolean.class);
    }

    @Override
    public Object send() {
        return requester.with("field","blockAfterWarheadDetonation").get().get();
    }
}
