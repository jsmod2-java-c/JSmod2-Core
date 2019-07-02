package cn.jsmod2.network.protocol.server;

import cn.jsmod2.core.protocol.GetPacket;

public class GetPortPacket extends GetPacket {

    public static final int ID = 0x5f;

    public GetPortPacket() {
        super(0x5f, Integer.class);
    }

    @Override
    public Integer send() {
        return (Integer)(requester.with("field","port").get().get());
    }
}
