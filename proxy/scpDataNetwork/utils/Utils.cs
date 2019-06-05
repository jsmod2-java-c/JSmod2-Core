using System;

namespace scpDataNetwork.utils
{
    public class Utils
    {
        public static int getPacketId(string message)
        {
            string str = System.Text.Encoding.UTF8.GetString(Convert.FromBase64String(message));
            int id;
            int idIndex = str.IndexOf("-");
            if (idIndex == -1)
            {
                throw new Exception("the message is not 'jsmod2-protocol'");
            }
            str = str.Substring(0,idIndex);
            int.TryParse(str,out id);
            return id;
        }
    }
}