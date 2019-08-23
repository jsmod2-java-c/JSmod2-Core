package cn.jsmod2.network.protocol.map.elevator;



public class SetElevatorLockedPacket extends SetElevatorPacket {

    public static final int ID = 122;

    public boolean locked;

    public SetElevatorLockedPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("locked",locked).to();
    }
}
