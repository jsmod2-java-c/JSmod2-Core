
/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.core.protocol;

import cn.jsmod2.core.RegisterTemplate;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.ex.ProtocolException;
import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.utils.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * jsmod2数据包编码是通过序列化为json，并编码为base64所得
 * (= = ])有啥错记得告诉作者，C#端也是以此为规则
 *
 * 发包可以带尾部请求(尾部请求未来将淘汰)
 *
 * 接收包则不能带尾部请求
 *
 * 并且可以注入一个特定的对象
 *
 * id-{主对象},字段:{对应对象}...~尾部附带消息
 *
 * 2019.5.4 20:22 开工
 *
 * @author magiclu550 #(code) binary packet
 */

public abstract class BinaryStream {

    public static final String ID = "id";

    public Map<Class<? extends DataPacket>,Integer> dataPackets;

    private Properties properties;

    private int id;

    public BinaryStream(int id){
        dataPackets = new HashMap<>();
        for(RegisterTemplate template:Server.getSender().getServer().getRegisters()){
            dataPackets.putAll(template.getPackets());
        }
        this.id = id;
        properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
    }

    public BinaryStream(){
        dataPackets = new HashMap<>();
        for(RegisterTemplate template:Server.getSender().getServer().getRegisters()){
            dataPackets.putAll(template.getPackets());
        }
        this.id = dataPackets.get(this.getClass());
        properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
    }

    public int getId(){
        return id;
    }


    public String[] splitJson(String json){
        return json.split("\\|");
    }
    /**
     * 编码一个对象
     * @param o
     * @return
     * @throws UnsupportedEncodingException
     */

    public byte[] dataObjectEncode(Object o) {
        return dataObjectEncodeWithEnd(o,"");
    }

    public byte[] dataObjectEncodeWithEnd(Object o,String end){

        try{
            if(!end.equals("")){
                end = "~"+end;
            }
            String json = JSON.toJSONString(o)+end;
            String packet = id+"-"+json;
            return dataJsonEncode(packet);
        }catch (Exception e){
            ServerLogger.getLogger().multiError(getClass(),e.getMessage(),"","");
            return null;
        }
    }

    protected byte[] dataJsonEncode(String message) throws Exception{
        byte[] bytes = message.getBytes(properties.getProperty("encode"));
        return (new String(org.apache.commons.codec.binary.Base64.encodeBase64(bytes))+";").getBytes();
    }


    public <T> List<T> dataListDecode(byte[] data, Class<T> clz){
        try {
            String json = getDefaultJson(data);
            String[] props = splitJson(json);
            json = props[0];
            List<T> list = JSON.parseArray(json,clz);
            return list;
        }catch (Exception e){
            throw new ProtocolException("The jsmod2 protocol is error",e);
        }
    }

    private String getDefaultJson(byte[] data) throws Exception{
        byte[] packetBytes = Base64.decodeBase64(data);
        packetBytes = Arrays.copyOf(packetBytes, Utils.getLen(packetBytes));
        String json = new String(packetBytes,properties.getProperty("decode"));
        json = json.substring(json.indexOf("-")+1);
        if(json.contains("~")) {
            json = json.substring(0, json.indexOf("~"));
        }
        return json;
    }

    public <T> T dataObjectDecode(byte[] data,Class<T> clz){
        try{
            String json = getDefaultJson(data);

            //{main-object}|player-xxx:xxx|team-class:xxx
            String[] props = splitJson(json);
            json = props[0];
            Object o = JSONObject.parseObject(json,clz);
            if(o==null){
                o = clz.newInstance();
            }
            String[] fields = new String[props.length-1];
            if(fields.length>0) {
                System.arraycopy(props, 1, fields, 0, fields.length);
                insertField(fields, o);
            }
            return clz.cast(o);
        }catch (Exception e){
            throw new ProtocolException("The jsmod2 protocol is error",e);
        }
    }

    private void insertField(String[] props,Object o) throws Exception{
        for(int i = 0;i<props.length;i++){
            String[] key_value = props[i].split(":");
            String[] fields = key_value[0].split("-");
            Object field = o;
            for(int j = 0;j<fields.length-1;j++){
                field = invokeGetMethod(field,fields[j]);
            }
            invokeSetMethod(field,fields[fields.length-1],key_value[1]);
        }
    }

    private Object invokeGetMethod(Object o,String field) throws Exception{
        StringBuilder builder = new StringBuilder((field.charAt(0)+"").toUpperCase());
        String first = "get"+builder.append(field.substring(1));
        return getMethod(o.getClass(),first).invoke(o);
    }

    private void invokeSetMethod(Object o,String field,String value) throws Exception{
        Field field1 = getField(o.getClass(),field);
        field1.setAccessible(true);
        Class<?> clz = field1.getType();
        Object object;
        try {
            object = JSON.parseObject(value, clz);
        }catch (JSONException e){
            object = value;
        }
        field1.set(o,object);
    }

    private Field getField(Class clz,String field) throws NoSuchFieldException{
        while (!clz.equals(Object.class)){
            if(hasField(clz,field)){
                Field field1 = clz.getDeclaredField(field);
                field1.setAccessible(true);
                return field1;
            }
            clz = clz.getSuperclass();
        }
        return null;
    }

    private boolean hasField(Class clz,String field){
        try {
            clz.getDeclaredField(field);
            return true;
        }catch (NoSuchFieldException e){

        }
        return false;
    }

    private Method getMethod(Class clz,String method) throws Exception{
        while (!clz.equals(Object.class)) {

            if(hasMethod(clz,method)){
                return clz.getMethod(method);
            }
            clz = clz.getSuperclass();

        }
        return null;
    }

    private boolean hasMethod(Class clz,String method){
        try{
            clz.getDeclaredMethod(method);
            return true;
        }catch (NoSuchMethodException e){
        }
        return false;
    }
}
