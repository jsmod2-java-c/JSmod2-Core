package cn.jsmod2.network.protocol.map.door;

public class SetDoorDontOpenOnWarheadPacket extends SetDoorPacket {

    public boolean dontOpenOnWarhead;

    public static final int ID = 113;

    public SetDoorDontOpenOnWarheadPacket(){
        super(ID);
    }

    @Override
    public void send() {
        requester.with("dontOpenOnWarhead",dontOpenOnWarhead).to();
    }
}
