package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.api.map.Elevator;

public class GetElevatorsPacket extends GetMapPacket {


    public static final int ID = 153;

    public GetElevatorsPacket() {
        super(ID, Elevator.class);
    }

    @Override
    public Object send() {
        return null;
    }
}
