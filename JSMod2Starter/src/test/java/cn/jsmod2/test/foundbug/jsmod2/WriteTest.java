package cn.jsmod2.test.foundbug.jsmod2;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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

}
