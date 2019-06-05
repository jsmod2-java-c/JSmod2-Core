package net.noyark.test.foundbug.jsmod2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Base64;

public class TestEncode{

    @Test
    public void encode() throws Exception{
//        System.out.println(JSON.toJSONString("11"));
//        byte[] bytes = Base64.getEncoder().encode("1-{stop}".getBytes());
//        System.out.println(dataObjectDecode(Base64.getEncoder().encode("1-{\"SaaLo\":\"1\"}".getBytes()),ObjectC.class));
        ObjectC c = new ObjectC();
        String json = JSON.toJSONString(c);

        System.out.println(json);
        ObjectC o = JSONObject.parseObject("{\"saalo\":12,\"test\":0}",ObjectC.class);

        System.out.println(o);
    }

    public byte[] dataObjectEncode(Object o) throws UnsupportedEncodingException {
        String json = JSON.toJSONString(o);
        String packet = "1-"+json;
        byte[] bytes = packet.getBytes();
        return Base64.getEncoder().encode(bytes);
    }

    public <T> T dataObjectDecode(byte[] data,Class<T> clz) throws UnsupportedEncodingException {
        byte[] packetBytes = Base64.getDecoder().decode(data);
        String json = new String(packetBytes);
        json = json.substring(("1-").length());
        Object o = JSONObject.parseObject(json,clz);
        System.out.println(o);
        return (T)o;
    }

    @Test
    public void mem() throws Exception{

        DatagramSocket socket = new DatagramSocket(19937);

        DatagramPacket request = new DatagramPacket(new byte[65535], 65535);

        socket.receive(request);
        String message = new String(request.getData(), 0 , request.getLength());

        System.out.println(message);

        dataObjectDecode(message.getBytes("utf-8"),String.class);
    }
}
