package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.ServerRunner;
import cn.jsmod2.ServerTest;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Requester;
import cn.jsmod2.core.protocol.Response;
import cn.jsmod2.core.utils.Utils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ServerSocket;
import java.net.Socket;

@RunWith(ServerRunner.class)
public class PacketTest {

    //一个简单解析jsmod2协议的代码
    @Test
    public void reget() throws Exception{
        ServerSocket socket = new ServerSocket(19938);
        Socket socket1 = socket.accept();
        byte[] bytes = new byte[1024];
        socket1.getInputStream().read(bytes);
        int len = 0;
        for(byte b:bytes){
            if(b != 0){
                len++;
            }
        }
        bytes = Utils.getFullBytes(socket1,bytes);
        String message = new String(bytes,0,len);
        String[] alls = message.split(";");
        for(String all:alls) {
            System.out.println(all);
        }


        socket1.getOutputStream().write("100-100".getBytes());

    }

    @Test
    @ServerTest
    public void test(){
        TestGet get = new TestGet();
        Object obj = get.send();
        System.out.println(obj);
    }

    class TestGet extends GetPacket{
        public TestGet() {
            super(0x89,User.class);
        }

        @Override
        public Object send() {
            try {
                Requester requester = this.requester.with("get", "你好").end("a");
                System.out.println(requester.getString());
                System.out.println(new String(java.util.Base64.getEncoder().encode(requester.getString().getBytes("UTF-8"))));
                Response response = requester.get();
                Object obj = getType().cast(response.get());
                System.out.println(requester.getString());
                return obj;
            }catch (Exception e){
                e.printStackTrace();
            }
            //MTM3LXsiZ2V0Ijoi5L2g5aW9IiwiaWQiOjEzN31+YQ==
            //MTM3LXsiZ2V0Ijoi5L2g5aW9IiwiaWQiOjEzN31+YQ==
            return null;
        }
    }



}


class User{
    String name;
    String have;
    private int id;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PlayerTest{" +
                "name='" + name + '\'' +
                ", have='" + have + '\'' +
                ", id=" + id +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHave() {
        return have;
    }

    public void setHave(String have) {
        this.have = have;
    }
}

