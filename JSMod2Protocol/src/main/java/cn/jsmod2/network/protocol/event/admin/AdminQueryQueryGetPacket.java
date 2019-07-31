package cn.jsmod2.network.protocol.event.admin;

import cn.jsmod2.network.protocol.event.packet.EventGetPacket;

public class AdminQueryQueryGetPacket extends EventGetPacket {

    public static final int ID = 106;

    public AdminQueryQueryGetPacket() {
        super(ID, String.class);
    }

    @Override
    public String send() {
        return (String) requester.with("field","query").get().get();
    }
}
