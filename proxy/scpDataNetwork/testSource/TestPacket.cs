using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using Newtonsoft.Json;
using scpDataNetwork.network;
using scpDataNetwork.utils;

namespace scpDataNetwork.testSource
{
   
    public class TestPacket
    {
        private static IPEndPoint ip;
        
        private static MainClass main;

        private static Socket socket;
        public static void Main(string[] args)
        {
            /**
            ip = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 19937);//connect the java smod2
            Socket server = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            IPEndPoint ip_this = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 19938);
            server.Bind(ip_this);//with ip
            socket = server;
            byte[] datas = new TestPacket1().encode();
            while (true)
            {
                // ReSharper disable once ENC0001
                socket.SendTo(datas,datas.Length, SocketFlags.None, ip);

                byte[] datas_re = new  byte[0xfff];

                EndPoint Remote = ip;

                int inde = socket.ReceiveFrom(datas_re,ref Remote);
            
                String str = Encoding .ASCII .GetString (datas_re ,0,inde);
            
                Console.WriteLine(Utils.getPacketId(str));
            }
            */
            String json = JsonConvert.SerializeObject(new Test01());
            Object o = JsonConvert.DeserializeObject(json, typeof(Test01));
            Console.WriteLine(o);
        }
        public static byte[] dataObjectEncode(object o)
        {
            string json = JsonConvert.SerializeObject(o);
            byte[] byteArray = System.Text.Encoding.UTF8.GetBytes (json);
            string base64 = Convert.ToBase64String(byteArray);
            return System.Text.Encoding.UTF8.GetBytes(base64);
        }

        public static object dataObjectDecode(byte[] bytes,Type type)
        {
            string str = System.Text.Encoding.UTF8.GetString(Convert.FromBase64String(System.Text.Encoding.UTF8.GetString(bytes)));
            Console.WriteLine(str);
            object o = JsonConvert.DeserializeObject(str,type);
            return o;
        }
    }
}