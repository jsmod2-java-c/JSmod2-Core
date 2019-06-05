
namespace scpDataNetwork
{
    public class PacketManager
    {
        private static PacketManager manager;

        static PacketManager()
        {
            manager = new PacketManager();
        }

        private PacketManager()
        {
            
        }
        
        public static PacketManager getManager()
        {
            return manager;
        }
        /**
         * 包在这里做解析
         * base64 message包信息
         * id 包号
         */
        public void manageMethod(string message,int id)
        {
            byte[] bytes = System.Text.Encoding.UTF8.GetBytes(message);
            
        }
    }
}