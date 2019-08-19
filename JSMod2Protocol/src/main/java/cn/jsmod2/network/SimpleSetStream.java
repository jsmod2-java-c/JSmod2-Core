package cn.jsmod2.network;

import cn.jsmod2.network.protocol.event.newstream.EventValueSetStream;

/**
 * 设置某个非单例api对象的属性时使用
 * 可以设置枚举 基本类型 api值
 */
public class SimpleSetStream {

    private EventValueSetStream setStream;

    public SimpleSetStream(){
        setStream = new EventValueSetStream();
    }

    public void write(String id,String key,Object value){
        setStream.playerName = id;
        setStream.name = key;
        setStream.value = value;
        setStream.send();
    }

}
