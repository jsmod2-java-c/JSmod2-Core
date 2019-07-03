package cn.jsmod2.core.protocol;

import cn.jsmod2.core.utils.Future;

import java.util.List;

public class Response {

    public Future future;

    public GetPacket packet;

    public Class<?> type;


    public Object get(){
        if(type == null){
            type = packet.getType();
        }

        return packet.dataObjectDecode(future.get(),this.type);
    }

    public List getArray(){

        if(type == null){
            type = packet.getType();
        }

        return packet.dataListDecode(future.get(),this.type);
    }


}
