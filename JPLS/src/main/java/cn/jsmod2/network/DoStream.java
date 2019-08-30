package cn.jsmod2.network;


import cn.jsmod2.core.protocol.SetPacket;


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
