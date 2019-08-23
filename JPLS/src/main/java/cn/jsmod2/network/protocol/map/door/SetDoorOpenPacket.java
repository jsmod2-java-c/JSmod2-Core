package cn.jsmod2.network.protocol.map.door;

public class SetDoorOpenPacket extends SetDoorPacket {

    public boolean isOpen;

    public static final int ID = 109;

    public SetDoorOpenPacket() {
        super(ID);
    }

    @Override
    public void send() {
        this.requester.with("isOpen",isOpen).to();
    }
}
