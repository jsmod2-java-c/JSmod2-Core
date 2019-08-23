package cn.jsmod2.network.protocol.map.elevator;

import cn.jsmod2.api.map.ElevatorStatus;

public class GetElevatorStatusPacket extends GetElevatorPacket {

    public static final int ID = 129;

    public GetElevatorStatusPacket() {
        super(ID, ElevatorStatus.class);
    }

    @Override
    public ElevatorStatus send() {
        return (ElevatorStatus) requester.with("field","elevatorStatus").get().get();
    }
}
