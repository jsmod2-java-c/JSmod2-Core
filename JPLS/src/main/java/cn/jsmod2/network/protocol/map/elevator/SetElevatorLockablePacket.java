package cn.jsmod2.network.protocol.map.elevator;

public class SetElevatorLockablePacket extends SetElevatorPacket {

    public static final int ID = 124;

    public boolean lockable;

    public SetElevatorLockablePacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("lockable",lockable).to();
    }
}
