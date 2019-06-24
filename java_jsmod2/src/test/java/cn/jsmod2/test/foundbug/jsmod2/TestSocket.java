package cn.jsmod2.test.foundbug.jsmod2;


import cn.jsmod2.core.utils.Utils;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Base64;

public class TestSocket {

    @Test
    public void sendPacket() throws Exception{
        DatagramSocket socket = new DatagramSocket(19937);
        while (true){
            sendPacket(socket);
        }
    }

    public void sendPacket(DatagramSocket socket){

        Utils.TryCatch(()->{
            byte[] encode = Base64.getEncoder().encode("1-你好世界".getBytes());
            DatagramPacket pack = new DatagramPacket(encode,encode.length, InetAddress.getByName("127.0.0.1"),19938);
            socket.send(pack);
        });
    }

}
