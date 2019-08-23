package cn.jsmod2.network.protocol.map.generator;

import cn.jsmod2.core.protocol.SetPacket;

//143
public class SetGeneratorTimeLeftPacket extends SetPacket {

    public static final int ID = 143;

    public float timeLeft;

    public SetGeneratorTimeLeftPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("timeLeft",timeLeft).to();
    }
}
