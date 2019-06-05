
using Smod2.API;

namespace scpDataNetwork.network
{
    public class ServerPacket: DataPacket
    {

        public Server server { get; set; }

        public ServerPacket() : base(0x00)
        {
            
        }
        public override object decode(byte[] datas)
        {
            return dataObjectDecode(datas,typeof(Server));
        }

        public override byte[] encode()
        {
            return dataObjectEncode(server);
        }
    }
}