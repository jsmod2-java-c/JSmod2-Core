package cn.jsmod2.network;

import cn.jsmod2.core.protocol.SetPacket;

public class DoStream extends SetPacket {

    public String method;

    public DoStream() {
        super(190);
    }

    @Override
    public void send() {
        requester.with(DO,method).to();
    }
}
