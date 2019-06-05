using System;

/**
 * @author MagicLu550 #(code) jsmod2
 */

namespace scpDataNetwork
{
    public class Message
    {
        public static void load(string msg)
        {
            MainClass.getInstance().Info("[Network]"+msg);
        }
    }
}