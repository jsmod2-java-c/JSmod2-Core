package cn.jsmod2.network.protocol.map.door;

public class SetDoorDestoryPacket extends SetDoorPacket {

    private boolean destory;

    public static final int ID = 111;

    public SetDoorDestoryPacket() {
        super(ID);
    }

    @Override
    public void send() {
        this.requester.with("destory",destory).to();
    }
}
