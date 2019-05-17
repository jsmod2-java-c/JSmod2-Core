package net.noyark.scpslserver.jsmod2.network;

import net.noyark.scpslserver.jsmod2.event.Event;

public class EventBinaryStream extends BinaryStream {


    public EventBinaryStream(){
        super(0xffff);
    }

    public <T> T encode(Class<T> event,byte[] bytes){
        return event.cast(dataObjectDecode(bytes,event));
    }

}
