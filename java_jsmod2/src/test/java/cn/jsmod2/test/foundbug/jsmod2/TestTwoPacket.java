package cn.jsmod2.test.foundbug.jsmod2;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestTwoPacket {

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
    @Test
    public void send() throws Exception{
        DatagramSocket socket = new DatagramSocket();
        new Thread1(socket).start();
        new Thread2(socket).start();
    }

    class Thread1 extends Thread{

        DatagramSocket socket;

        public Thread1(DatagramSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            DatagramPacket pack;
            try{
                for(int i = 0;i<5;i++)
                    socket.send(new DatagramPacket(("Thread1-"+i).getBytes(),("Thread1-"+i).getBytes().length, InetAddress.getByName("127.0.0.1"),8889));
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class Thread2 extends Thread{

        DatagramSocket socket;

        public Thread2(DatagramSocket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            DatagramPacket pack;
            try{
                for(int i = 0;i<1000;i++)
                    socket.send(new DatagramPacket(("Thread2-"+i).getBytes(),("Thread2-"+i).getBytes().length, InetAddress.getByName("127.0.0.1"),8889));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
