package cn.jsmod2.network.protocol.map.door;

public class SetDoorBlockAfterWarheadDetonationPacket extends SetDoorPacket {

    public static final int ID = 115;

    public boolean blockAfterWarheadDetonation;

    public SetDoorBlockAfterWarheadDetonationPacket(){
        super(ID);
    }

    @Override
    public void send() {
        requester.with("blockAfterWarheadDetonation",blockAfterWarheadDetonation).to();
    }
}
