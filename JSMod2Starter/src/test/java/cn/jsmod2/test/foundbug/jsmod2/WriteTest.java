package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.core.math.Vector;
import com.alibaba.fastjson.JSON;
import org.junit.Test;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class WriteTest {

    @Test
    public void send() throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",19135));
        Thread.sleep(10000);
        socket.getOutputStream().write("hello".getBytes());
        socket.getInputStream().read();
    }

    @Test
    public void rece() throws Exception{
        ServerSocket socket = new ServerSocket(19135);
        Socket socket1 = socket.accept();
        int i = socket1.getInputStream().read();
        socket1.getOutputStream().write("ok".getBytes());
        socket1.close();
        socket1.close();
        System.out.println("finish");
    }

    @Test
    public void testMap(){
        Map<String,String> map = JSON.parseObject("{\"(1.1-2.3-4.1)\":\"(1.2-4.3-4.6)\"}\n",Map.class);
        java.util.Map<Vector,Vector> vectorMap = new HashMap<>();
        for(java.util.Map.Entry<String,String> entry:map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            vectorMap.put(getVector(key),getVector(value));
        }
        System.out.println(vectorMap);
    }

    private Vector getVector(String str){
        String[] xyz = str.substring(str.indexOf("(")+1,str.lastIndexOf(")")).split("-");
        double x = Double.parseDouble(xyz[0]);
        double y = Double.parseDouble(xyz[1]);
        double z = Double.parseDouble(xyz[2]);
        return new Vector(x,y,z);
    }


}
