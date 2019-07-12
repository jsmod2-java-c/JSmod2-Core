package cn.jsmod2.core.protocol;

import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.utils.Future;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class Response {

    public Future future;

    public GetPacket packet;

    public Class<?> type;

    //{}|
    //id-{}|a:{}|a:{}@!id-{}@!{}@!{} 多个协议并行
    public Object get(){
        if(type == null){
            type = packet.getType();
        }

        return packet.dataObjectDecode(future.get(),this.type);
    }


    //将一个json协议转化为数组
    public List getArray(){

        if(type == null){
            type = packet.getType();
        }

        return packet.dataListDecode(future.get(),this.type);
    }

    //将多个json协议获得对象转化为数组，主要用于Item等实体类获取(Map中)
    //保证每个的协议转化出的对象是一致的
    //@!用于分割
    public List getProtocolArray(boolean getArray){
        if(type == null){
            type = packet.getType();
        }
        List<Object> list = new ArrayList<>();
        Properties properties =  Server.getSender().getServer().serverProp;
        //转化为base64解密后的，之后再加密 getBytes
        try {
            String message = new String(Base64.getDecoder().decode(future.get()), properties.getProperty(FileSystem.SERVER_DECODE));
            String[] protocols = message.split("@!");
            if(!getArray) {
                for (String protocol : protocols) {
                    list.add(packet.dataObjectDecode(encode(protocol,properties), this.type));
                }
            }else{
                for(String protocol:protocols){
                    list.add(packet.dataListDecode(encode(protocol,properties),this.type));
                }
            }
        }catch (UnsupportedEncodingException e){

        }
        return list;
    }

    private byte[] encode(String protocol,Properties properties) throws UnsupportedEncodingException{
        
        return Base64.getEncoder().encode(protocol.getBytes(properties.getProperty(FileSystem.SERVER_ENCODE)));
    }


}
