package cn.jsmod2.scpslserver.network;

import cn.jsmod2.scpslserver.Register;
import cn.jsmod2.scpslserver.Server;
import cn.jsmod2.scpslserver.ex.ProtocolException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.jsmod2.scpslserver.FileSystem;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;

/**
 * jsmod2数据包编码是通过序列化为json，并编码为base64所得
 * (= = ])有啥错记得告诉作者，C#端也是以此为规则
 *
 * 2019.5.4 20:22 开工
 *
 * @author magiclu550 #(code) binary packet
 */

public abstract class BinaryStream {

    public Map<Class<? extends DataPacket>,Integer> dataPackets = Register.getInstance().getPackets();

    private Properties properties;

    private int id;

    public BinaryStream(int id){
        this.id = id;
        properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
    }

    public BinaryStream(){
        this.id = dataPackets.get(this.getClass());
        properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
    }

    public int getId(){
        return id;
    }


    public String[] splitJson(String json){
        return json.split(",");
    }
    /**
     * 编码一个对象
     * @param o
     * @return
     * @throws UnsupportedEncodingException
     */

    public byte[] dataObjectEncode(Object o) {
        try{
            String json = JSON.toJSONString(o);
            String packet = id+"-"+json;
            byte[] bytes = packet.getBytes(properties.getProperty("encode"));
            return Base64.getEncoder().encode(bytes);
        }catch (Exception e){
            return null;
        }
    }


    public <T> T dataObjectDecode(byte[] data,Class<T> clz){
        try{
            byte[] packetBytes = Base64.getDecoder().decode(data);
            String json = new String(packetBytes,properties.getProperty("decode"));
            json = json.substring((id+"-").length());
            //{main-object},player-xxx:xxx,team-class:xxx
            String[] props = splitJson(json);
            json = props[0];
            Object o = JSONObject.parseObject(json,clz);
            for(int i = 0;i<props.length;i++){
                String[] key_value = props[i].split(":");
                String[] fields = key_value[0].split("-");
                Object field = o;
                for(int j = 0;j<fields.length-1;j++){
                    field = invokeGetMethod(field,fields[j]);
                }
                invokeSetMethod(field,fields[fields.length-1],key_value[1]);
            }
            return clz.cast(o);
        }catch (Exception e){
            throw new ProtocolException("The jsmod2 protocol is error",e);
        }
    }

    private Object invokeGetMethod(Object o,String field) throws Exception{
        StringBuilder builder = new StringBuilder((field.charAt(0)+"").toUpperCase());
        String first = "get"+builder.append(field.substring(1));
        return o.getClass().getMethod(first).invoke(o);
    }
    private void invokeSetMethod(Object o,String field,String value) throws Exception{
        Field field1 = o.getClass().getDeclaredField(field);
        field1.setAccessible(true);
        Class<?> clz = field1.getType();
        Object object = JSON.parseObject(value,clz);
        field1.set(o,object);
    }
}
