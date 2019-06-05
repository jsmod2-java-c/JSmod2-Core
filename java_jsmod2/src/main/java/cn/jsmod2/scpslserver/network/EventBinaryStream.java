package cn.jsmod2.scpslserver.network;

public class EventBinaryStream extends BinaryStream {


    public EventBinaryStream(){
        super(0xffff);
    }

    public <T> T encode(Class<T> event,byte[] bytes){
        return event.cast(dataObjectDecode(bytes,event));
    }

}
