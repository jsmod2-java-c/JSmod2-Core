package cn.jsmod2.network.protocol.event.admin;

import cn.jsmod2.network.protocol.event.packet.EventSetPacket;

public class AdminQueryQuerySetPacket extends EventSetPacket {

    public String query;

    public static final int ID = 105;

    public AdminQueryQuerySetPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("query",query).to();
    }
}
