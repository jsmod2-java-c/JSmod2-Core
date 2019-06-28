package cn.jsmod2.test.foundbug.jsmod2;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;

public class TestTwoPacket {

    private static int count;

    //服务端
    @Test
    public void receive() throws Exception{
        DatagramSocket socket = new DatagramSocket(8889);
        while(true) {
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            socket.receive(request);
            System.out.println(11);
            System.out.println(new String(request.getData()));
        }
    }


    //客户端

    public static void main(String[] args) throws Exception{
//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("127.0.0.1",19935));
//        socket.getOutputStream().write(Base64.getEncoder().encode("111-hello".getBytes()));
//        Thread.sleep(10);
//        socket.getOutputStream().write("11".getBytes());

            //DatagramSocket socket = new DatagramSocket();
//            new Thread1().start();
//            new Thread2().start();
        System.out.println("😄");
    }


    static class Thread1 extends Thread{

        Socket socket;

        public Thread1()throws Exception {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1",19935));

        }

        @Override
        public void run() {
            try{

                for(int i = 0;i<10000000;i++) {
                    //MTk5OTk5LVRocmVhZDItMjU4NQ==
                    byte[] bytes = Base64.getEncoder().encode(("9000909-你好，我的世界，helloworld,thanks"+i).getBytes());
                    bytes = Arrays.copyOf(bytes,bytes.length+1);
                    bytes[bytes.length-1] = ';';
                    socket.getOutputStream().write(bytes);
                    synchronized (TestTwoPacket.class){
                        count++;
                        System.out.println(count);
                    }
                }
            }catch (Exception e){
                System.out.println(12);
                e.printStackTrace();
            }

        }
    }
    static class Thread2 extends Thread{

        Socket socket;

        public Thread2()throws Exception {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1",19935));

        }

        @Override
        public void run() {
            try{

                for(int i = 0;i<100000;i++) {
                    //MTk5OTk5LVRocmVhZDItMjU4NQ==
                    byte[] bytes = Base64.getEncoder().encode(("9000909-你好，我的世界，helloworld,thanks"+i).getBytes());
                    bytes = Arrays.copyOf(bytes,bytes.length+1);
                    bytes[bytes.length-1] = ';';
                    socket.getOutputStream().write(bytes);
                    synchronized (TestTwoPacket.class){
                        count++;
                        System.out.println(count);
                    }
                }
            }catch (Exception e){
                System.out.println(12);
                e.printStackTrace();
            }

        }
    }

}
