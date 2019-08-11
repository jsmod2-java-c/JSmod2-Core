package cn.jsmod2.network.protocol.map.elevator;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetElevatorPacket extends SetPacket {

    public SetElevatorPacket(int id) {
        super(id);
        requester.with("type","elevator");
    }
}