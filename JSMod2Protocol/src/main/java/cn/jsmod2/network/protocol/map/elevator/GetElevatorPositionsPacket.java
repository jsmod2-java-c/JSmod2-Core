package cn.jsmod2.network.protocol.map.elevator;

import cn.jsmod2.core.math.Vector;

import java.util.List;

public class GetElevatorPositionsPacket extends GetElevatorPacket {

    public static final int ID = 127;

    public GetElevatorPositionsPacket() {
        super(ID, Vector.class);
    }

    @Override
    public List send() {
        return requester.with("field","positions").get().getArray();
    }
}
