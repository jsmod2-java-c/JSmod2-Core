package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.network.PacketSender;

public class SimpleMapMethodPacket extends GetPacket {

    public static final int ID = 196;


    public String method;

    public String[] args;

    public boolean isWrite;

    public int getTypes;


    public SimpleMapMethodPacket( Class<?> type) {
        super(ID, type);
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
