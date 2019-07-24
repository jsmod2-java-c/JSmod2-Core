package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.DefaultServer;
import cn.jsmod2.api.event.player.PlayerJoinEvent;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.Application;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.UUID;

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
        socket.getOutputStream().write(Base64.getEncoder().encode(("45-{}|playerName:"+ UUID.randomUUID()).getBytes()));
        socket.close();
    }

    @Test
    public void eventId() throws Exception{
        Event event = new PlayerJoinEvent();
        Method method = getMethod(event.getClass(),"getPlayer");
        System.out.println(method);
    }

    private Method getMethod(Class clz, String method) throws Exception{
        while (!clz.equals(Object.class)) {
            clz = clz.getSuperclass();
            if(hasMethod(clz,method)){
                return clz.getMethod(method);
            }
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
