package cn.jsmod2.network;

import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Response;

public class DoGetStream extends GetPacket {

    public String method;

    public String[] args;

    public int getType;

    public DoGetStream(Class<?> type) {
        super(190,type);
    }

    @Override
    public Object send() {
        return read(getType);
    }

    private Object read(int getType){
        PacketSender.req(requester,method,args);
        Response response = requester.get();
        return PacketSender.getResponseValue(response,getType);
    }
}
