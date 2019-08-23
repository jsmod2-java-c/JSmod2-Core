package cn.jsmod2.network.protocol.map.elevator;

import cn.jsmod2.api.map.ElevatorType;

public class GetElevatorTypePacket extends GetElevatorPacket{

    public static final int ID = 128;

    public GetElevatorTypePacket() {
        super(ID, ElevatorType.class);
    }

    @Override
    public ElevatorType send() {
        return (ElevatorType) requester.with("field","elevatorType").get().get();
    }
}
