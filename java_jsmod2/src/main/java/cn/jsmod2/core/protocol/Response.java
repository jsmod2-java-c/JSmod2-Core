package cn.jsmod2.core.protocol;

import cn.jsmod2.core.utils.Future;

public class Response {

    public Future future;

    public GetPacket packet;

    public Object get(){
        return packet.dataObjectDecode(future.get(),packet.getType());
    }


}
