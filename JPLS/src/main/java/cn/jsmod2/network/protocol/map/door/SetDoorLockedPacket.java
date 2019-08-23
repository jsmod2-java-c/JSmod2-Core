package cn.jsmod2.network.protocol.map.door;

public class SetDoorLockedPacket extends SetDoorPacket {

    public static final int ID = 117;

    public boolean isLocked;

    public SetDoorLockedPacket(){
        super(ID);
    }

    @Override
    public void send() {
        requester.with("locked",isLocked).to();
    }
}
