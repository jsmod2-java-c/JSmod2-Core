package cn.jsmod2.network;

import cn.jsmod2.core.protocol.Response;
import cn.jsmod2.core.protocol.SetPacket;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

public class DoStream extends SetPacket {

    public String method;

    public String[] args;

    public DoStream() {
        super(190);
    }

    @Override
    public void send() {
        PacketSender.req(requester,method,args);
        requester.to();
    }





}
