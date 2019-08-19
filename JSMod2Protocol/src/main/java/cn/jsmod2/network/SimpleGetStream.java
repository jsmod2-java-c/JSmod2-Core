package cn.jsmod2.network;

import cn.jsmod2.network.protocol.event.newstream.EventValueGetStream;

/**
 * 获取一个非单例api的某个特定值时使用
 * 可以获取枚举，基本类型值
 */
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

    public <T> T read(String id,String name,Class<T> returnType){
        return read(id,name,0,returnType);
    }

}
