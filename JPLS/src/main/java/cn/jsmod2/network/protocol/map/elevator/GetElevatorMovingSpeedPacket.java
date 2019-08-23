package cn.jsmod2.network.protocol.map.elevator;

public class GetElevatorMovingSpeedPacket extends GetElevatorPacket {

    public static final int ID = 125;

    public GetElevatorMovingSpeedPacket() {
        super(ID,Float.class);
    }

    @Override
    public Float send() {
        return (Float)requester.with("field","movingSpeed").get().get();
    }
}
