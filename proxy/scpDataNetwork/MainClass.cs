using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using scpDataNetwork.listener;
using scpDataNetwork.network;
using scpDataNetwork.utils;
using Smod2;
using Smod2.Attributes;


/**
 *
 *jsmod2和smod2的ip必须相同，除非做出更改
 * 
 * @author MagicLu550 #(code) jsmod2
 */

namespace scpDataNetwork
{
    [PluginDetails(
        author = "Magiclu550",
        name = "Network",
        description = "connect with java smod2",
        id = "net.noyark.NetWork",
        configPrefix = "ep",
        langFile = "scpDataNetwork",
        version = "1.0",
        SmodMajor = 3,
        SmodMinor = 4,
        SmodRevision = 1
    )]
    class MainClass : Plugin
    {
        private static IPEndPoint ip;
        
        private static MainClass main;

        private static Socket socket;
        public override void Register()
        {
            AddEventHandlers(new AdminQueryHandler());
        }

        public override void OnEnable()
        {
            main = this;
            Message.load("the dataNetwork is starting");
            Message.load("请确保您的java服务器的指定端口是开启的，使得java服务器可以连接");
            //jsmod2的端口
            int jsmod2_port;
            //本代理端口
            int this_int_port;
            //指定的ip
            String getIp;
            //读取配置文件
            Properties.readInfo(Server,out jsmod2_port,out this_int_port,out getIp);
            //获取jsmod2的ip对象
            ip = new IPEndPoint(IPAddress.Parse(getIp), jsmod2_port);//connect the java smod2
            
            Socket server = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            
            IPEndPoint this_ip = new IPEndPoint(IPAddress.Parse(getIp), this_int_port);
            
            server.Bind(this_ip);//绑定本机端口
            
            socket = server;
            
            ServerPacket packet = new ServerPacket();
            packet.server = Server;
            
            sendPacket(packet);//初始化服务器
            
            //把server对象发包
            Console.WriteLine("In Main: Listener Thread is starting");
            //接收包的线程，监听线程
            ListenerThread thread = new ListenerThread();

            Thread listenerThread = new Thread(thread.listenerThread);
            
            listenerThread.Start();//listener thread
            
        }
        
        
        public void sendPacket(DataPacket packet)
        {
            byte[] data = packet.encode();
            socket.SendTo(data, data.Length, SocketFlags.None, ip);
        }
        public override void OnDisable()
        {
            sendPacket(new ClosePacket());
            socket.Close();
        }

        public static MainClass getInstance()
        {
            return main;
        }

        public static Socket getSocket()
        {
            return socket;
        }
        
        /**
         * listener thread
         */
        private class ListenerThread
        {
            private String jsmod2_port;
            
            public void listenerThread()
            {
                while (true)
                {
                    //接收包的地方，ip为jsmod2的ip对象，采用jsmod2的端口
                    byte[] datas_re = new byte[0xfff];

                    EndPoint Remote = ip;

                    int inde = socket.ReceiveFrom(datas_re,ref Remote);
            
                    String message = Encoding .UTF8 .GetString (datas_re ,0,inde);
                    //get data packet
                    
                    int id = Utils.getPacketId(message);//获取jsmod2发包的ip
                    
                    //解析包方法
                    PacketManager.getManager().manageMethod(message,id);
                    
                }
            }
            //初始化jsmod2的信息
            public void sendServer()
            {
                while (true)
                {
                    ServerPacket packet = new ServerPacket();
                    packet.server = main.Server;
                    main.sendPacket(packet);
                }
            }
        }
    }
}