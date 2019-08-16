package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.SetPacket;

public class FemurBreakerMapPacket extends SetPacket {


    public static final int ID = 150;

    public boolean enable;

    public FemurBreakerMapPacket(){
        super(ID);
    }

    @Override
    public void send() {
        requester.with("enable",enable).to();
    }
}
