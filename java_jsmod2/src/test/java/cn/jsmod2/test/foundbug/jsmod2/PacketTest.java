package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.DefaultServer;
import cn.jsmod2.ServerRunner;
import cn.jsmod2.ServerTest;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Response;
import cn.jsmod2.core.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

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
            System.out.println(new String(Base64.getDecoder().decode(all)));
        }


        socket1.getOutputStream().write(Base64.getEncoder().encode("90-{\"name\":\"name\",\"have\":\"true\"}|id:1".getBytes()));

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
            Response response = requester.with("get","name").end("a").get();
            Object obj = getType().cast(response.get());
            return obj;
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
        return "User{" +
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

