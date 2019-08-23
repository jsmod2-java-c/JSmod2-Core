package cn.jsmod2.network.protocol.map.elevator;

public class UseElevatorPacket extends SetElevatorPacket{

    public static final int ID = 130;

    public UseElevatorPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with(DO,"use").to();
    }
}
