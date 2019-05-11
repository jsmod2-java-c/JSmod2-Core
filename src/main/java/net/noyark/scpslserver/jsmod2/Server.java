package net.noyark.scpslserver.jsmod2;

import java.io.IOException;
import java.net.*;

/**
 * jsmod2 server class
 *
 *
 */

public class Server {

    public static final int MAX_LENGTH = 65535;
    

    Server() throws IOException {

        while (true) {

            //接收数据包

            DatagramSocket socket = getSocket(19935);

            DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);

            socket.receive(request);

            String message = new String(request.getData(), 0 , request.getLength());

            if(message.startsWith("[2]stop")){
                break;
            }

            //发出数据包部分由C#插件决定，C#插件的Server中央处理java发出的请求
        }

    }
    //监听Smod2转发端接口
    public DatagramSocket getSocket(int port) throws SocketException {

        DatagramSocket socket = new DatagramSocket(port);

        return socket;
    }


}
