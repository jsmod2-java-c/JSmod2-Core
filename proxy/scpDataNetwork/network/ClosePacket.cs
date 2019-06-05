

namespace scpDataNetwork.network
{
    public class ClosePacket : DataPacket
    {
        
        public ClosePacket() : base(0x02)
        {
            
        }

        public override byte[] encode()
        {
            return dataObjectEncode(new Closeable());
        }

        public override object decode(byte[] datas)
        {
            return dataObjectDecode(datas,typeof(Closeable));
        }
    }

    public class Closeable
    {
        private string close { get; }

        public Closeable()
        {
            close = "close";
        }
    }
}