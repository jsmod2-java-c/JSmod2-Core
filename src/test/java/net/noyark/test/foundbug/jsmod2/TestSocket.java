package net.noyark.test.foundbug.jsmod2;


import net.noyark.scpslserver.jsmod2.network.DataPacket;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestSocket {

    @Test
    public void sendPacket() throws Exception{
        DatagramSocket socket = new DatagramSocket(19935);
        //sendPacket(socket,);
    }

    public void sendPacket(final DataPacket packet,DatagramSocket socket){

        Utils.TryCatch(()->{
            byte[] encode = packet.encode();
            DatagramPacket pack = new DatagramPacket(encode,encode.length, InetAddress.getByName("127.0.0.1"),19938);
            socket.send(pack);
        });
    }

}
