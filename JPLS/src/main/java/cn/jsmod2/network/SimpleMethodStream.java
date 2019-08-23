package cn.jsmod2.network;

import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.utils.Utils;

public class SimpleMethodStream extends GetPacket {

    public String method;

    public String[] args;

    public boolean isWrite;

    public int getTypes;

    public SimpleMethodStream(int id,Class<?> type){
        super(id,type);
    }

    @Override
    public Object send() {
        requester.with("method",method).with("args", Utils.arraysToString(args));
        if(isWrite){
            requester.with("write",true).to();
            return null;
        }else{
            requester.with("read",true);
        }
        return PacketSender.getResponseValue(requester.get(),getTypes);
    }
}
