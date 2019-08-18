package cn.jsmod2.network;

import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;

public class SimpleGetStream {

    private EventValueGetStream stream;

    public SimpleGetStream(Class<?> type){
        stream = new EventValueGetStream(type);
    }

    public <T> T read(String id,String name,int type,Class<T> returnType){
        stream.getType = type;
        stream.name = name;
        stream.playerName = id;
        return returnType.cast(stream.send());
    }

}
