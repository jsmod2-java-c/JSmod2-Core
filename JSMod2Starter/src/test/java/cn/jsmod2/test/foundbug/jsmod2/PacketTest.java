package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.DefaultServer;
import cn.jsmod2.ServerRunner;
import cn.jsmod2.ServerTest;
import cn.jsmod2.core.Application;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Requester;
import cn.jsmod2.core.protocol.Response;
import cn.jsmod2.core.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

@ServerApplication(DefaultServer.class)
public class PacketTest {

    //一个简单解析jsmod2协议的代码
    @Test
    public void eventRe(){
        Application.run(PacketTest.class,new String[]{});
    }

    @Test
    public void eventSend() throws Exception{
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress("127.0.0.1",19938));
        socket.connect(new InetSocketAddress("127.0.0.1",19935));
        socket.getOutputStream().write(Base64.getEncoder().encode("45-{\"name\":{\"a\":11}}".getBytes()));
        socket.close();
    }



}
