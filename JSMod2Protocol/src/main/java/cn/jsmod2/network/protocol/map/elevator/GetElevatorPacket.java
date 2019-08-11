package cn.jsmod2.network.protocol.map.elevator;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetElevatorPacket extends GetPacket {

    public GetElevatorPacket(int id, Class<?> type) {
        super(id, type);
        requester.with("type","elevator");
    }
}
