package net.noyark.scpslserver.jsmod2.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.noyark.scpslserver.jsmod2.FileSystem;
import net.noyark.scpslserver.jsmod2.Server;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;

/**
 * jsmod2数据包编码是通过序列化为json，并编码为base64所得
 * (= = ])有啥错记得告诉作者，C#端也是以此为规则
 *
 * 2019.5.4 20:22 开工
 *
 * @author magiclu550 #(code) binary packet
 */

public class BinaryStream {

    private Properties properties;

    private int id;

    public BinaryStream(int id){
        this.id = id;
        properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
    }

    public int getId(){
        return id;
    }

    /**
     * 编码一个对象
     * @param o
     * @return
     * @throws UnsupportedEncodingException
     */

    public byte[] dataObjectEncode(Object o) throws UnsupportedEncodingException {
        String json = JSON.toJSONString(o);
        String packet = id+"-"+json;
        byte[] bytes = packet.getBytes(properties.getProperty("encode"));
        return Base64.getEncoder().encode(bytes);
    }


    public <T> T dataObjectDecode(byte[] data,Class<T> clz) throws UnsupportedEncodingException {
        byte[] packetBytes = Base64.getDecoder().decode(data);
        String json = new String(packetBytes,properties.getProperty("decode"));
        json = json.substring((id+"-").length());
        Object o = JSONObject.parseObject(json,clz);
        return (T)o;
    }

}
