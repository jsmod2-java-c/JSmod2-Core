package cn.jsmod2.network;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.protocol.GetPacket;

public class SimpleFieldStream extends GetPacket {
    public Object value;

    public String field;

    public int getTypes;

    public boolean isWrite;//0 read 1 write

    public SimpleFieldStream(int id,Class<?> type) {
        super(id, type);
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
