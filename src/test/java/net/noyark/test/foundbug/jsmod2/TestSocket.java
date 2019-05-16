package net.noyark.test.foundbug.jsmod2;


import net.noyark.scpslserver.jsmod2.network.DataPacket;
import net.noyark.scpslserver.jsmod2.network.ServerInitPacket;
import net.noyark.scpslserver.jsmod2.utils.Utils;

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
            Thread.sleep(10000);
        }
    }

    public void sendPacket(DatagramSocket socket){

        Utils.TryCatch(()->{
            byte[] encode = Base64.getEncoder().encode("1111".getBytes());
            DatagramPacket pack = new DatagramPacket(encode,encode.length, InetAddress.getByName("127.0.0.1"),19938);
            socket.send(pack);
        });
    }

}
