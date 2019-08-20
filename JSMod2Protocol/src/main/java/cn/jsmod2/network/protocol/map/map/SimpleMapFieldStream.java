package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.network.PacketSender;

public class SimpleMapFieldStream extends GetPacket {

    public static final int ID = 196;

    public Object value;

    public String field;

    public int getTypes;

    public boolean isWrite;//0 read 1 write

    public SimpleMapFieldStream(Class<?> type) {
        super(ID, type);
    }

    @Override
    public Object send() {
        if(value instanceof ApiId){
            value = ((ApiId)value).getApiId();
            requester.with("apiId",true);
        }
        requester.with("value",value).with("field",field);
        if(isWrite){
            requester.with("write",true).to();
            return null;//无返回值
        }else{
            requester.with("read",true);
        }

        return PacketSender.getResponseValue(requester.get(),getTypes);//有返回值
    }
}
