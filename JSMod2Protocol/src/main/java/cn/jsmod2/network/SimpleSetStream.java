package cn.jsmod2.network;

import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;

public class SimpleSetStream {

    private EventValueSetStream setStream;

    public SimpleSetStream(){
        setStream = new EventValueSetStream();
    }

    public void write(String id,String key,String value){
        setStream.playerName = id;
        setStream.name = key;
        setStream.value = value;
        setStream.send();
    }

}
