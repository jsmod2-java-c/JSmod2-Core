package cn.jsmod2.network.protocol.map.elevator;

public class SetElevatorMovingSpeedPacket extends SetElevatorPacket{


    public static final int ID = 126;


    public float movingSpeed;

    public SetElevatorMovingSpeedPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("movingSpeed",movingSpeed).to();
    }
}
