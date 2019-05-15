package net.noyark.test.foundbug.jsmod2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.noyark.scpslserver.jsmod2.network.DataPacket;
import net.noyark.scpslserver.jsmod2.utils.Utils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TestEncode{

    @Test
    public void encode() throws Exception{
        System.out.println(JSON.toJSONString("11"));
        byte[] bytes = Base64.getEncoder().encode("1-{stop}".getBytes());
        System.out.println(dataObjectDecode(Base64.getEncoder().encode("1-{\"SaaLo\":\"1\"}".getBytes()),ObjectC.class));
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
        return (T)o;
    }
}
