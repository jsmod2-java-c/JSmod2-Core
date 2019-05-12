package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

import java.io.IOException;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jsmod2 server class
 *
 * @author magiclu550 #(code)jsmod2
 *
 */

public class Server {
    // 开辟线程
    // 监听线程 和 一个输入线程
    // 输入线程负责输入命令等

    private static Scanner scanner = new Scanner(System.in);

    private ILogger log;

    private Properties lang;

    private ExecutorService pool = Executors.newFixedThreadPool(5);
    /**用于将服务器对象传递给插件对象*/
    private Server server;

    private static CommandConsoleSender sender;

    public static final int MAX_LENGTH = 65535;
    

    Server(ILogger log, Properties lang) {

        this.log = log;

        this.lang = lang;

        this.server = this;

        sender = new CommandConsoleSender(server);

        pool.execute(new ListenerThread());


    }

    public static CommandConsoleSender getSender(){
        return sender;
    }

    //监听Smod2转发端接口
    public DatagramSocket getSocket(int port) throws SocketException {

        DatagramSocket socket = new DatagramSocket(port);

        return socket;
    }

    public void close(){
        log.info(lang.getProperty("end.finish"));
        System.exit(0);
    }

    public static Scanner getScanner(){
        return scanner;
    }


    /**
     * 服务器监听线程启动
     */
    private class ListenerThread implements Runnable{
        @Override
        public void run() {
            try{
                DatagramSocket socket = getSocket(19935);

                while (true) {

                    //接收数据包

                    DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);

                    socket.receive(request);

                    String message = new String(request.getData(), 0 , request.getLength());


                    if(message.startsWith("[2]")){
                        //停止服务端
                        socket.close();
                        close();
                    }
                    //发出数据包部分由C#插件决定，C#插件的Server中央处理java发出的请求
                    //专门设立发包的api
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
