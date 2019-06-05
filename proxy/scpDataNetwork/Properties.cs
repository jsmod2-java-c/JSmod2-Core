using System;
using System.IO;
using Smod2.API;

namespace scpDataNetwork
{
    public class Properties
    {
        private const string DEFAULT_JSMOD2_PORT = "19935";//jsmod2服务器端口

        private const string DEFAULT_THIS_PORT = "19938";//本插件的端口
        
        public static void readInfo(Server Server,out int jsmod2_port,out int this_int_port,out string getIp)
        {
            getIp = Server.IpAddress;
            //配置文件，指向端口
            if (!Directory.Exists("../jsmod2"))
            {
                Directory.CreateDirectory("../jsmod2");
                FileStream stream = File.Create("../jsmod2/conn.properties");
                StreamWriter writer = new StreamWriter(stream);
                writer.Write("jsmod2.port: "+DEFAULT_JSMOD2_PORT);
                writer.Write("jsmod2.ip: 127.0.0.1");
                writer.Write("this.port: "+DEFAULT_THIS_PORT);
                writer.Close();
            }

            StreamReader reader = File.OpenText(@"../jsmod2/conn.properties");
            String msg;
            String port_string = DEFAULT_JSMOD2_PORT;//目标机的端口号
            String this_port = DEFAULT_THIS_PORT;//本机端口号
            while ((msg = reader.ReadLine())!=null)
            {
                if (msg.StartsWith("jsmod2.port:"))
                {
                    string[] entry = msg.Replace(" ", "").Split(":");
                    if (entry.Length > 1)
                    {
                        port_string = entry[1];
                    }
                    else
                    {
                        port_string = DEFAULT_JSMOD2_PORT;
                    }
                }

                if (msg.StartsWith("jsmod2.ip:"))
                {
                    string[] entry = msg.Replace(" ", "").Split(":");
                    if (entry.Length > 1)
                    {
                        getIp = entry[1];
                    }
                    else
                    {
                        getIp = Server.IpAddress;
                    }
                }

                if (msg.StartsWith("this.port:"))
                {
                    string[] entry = msg.Replace(" ", "").Split(":");
                    if (entry.Length > 1)
                    {
                        this_port = entry[1];
                    }
                    else
                    {
                        this_port = DEFAULT_THIS_PORT;
                    }
                }
            }
            
            
            int.TryParse(port_string,out jsmod2_port);
            
            int.TryParse(this_port, out this_int_port);
        }
    }
}