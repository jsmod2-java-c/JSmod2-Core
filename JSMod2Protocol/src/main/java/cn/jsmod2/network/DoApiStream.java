package cn.jsmod2.network;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.protocol.SetPacket;


public class DoApiStream extends SetPacket {


    public Object value;

    public String method;

    public DoApiStream() {
        super(195);
    }

    @Override
    public void send() {
        if(value instanceof ApiId){
            value = ((ApiId) value).getApiId();
        }
        requester.with("value",value).with("method",method).to();
    }
}
