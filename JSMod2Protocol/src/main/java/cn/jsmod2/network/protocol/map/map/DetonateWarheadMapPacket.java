package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public class DetonateWarheadMapPacket extends SetPacket {

    public static final int ID = 149;


    public DetonateWarheadMapPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with(DO,"detonateWarhead").to();
    }
}
