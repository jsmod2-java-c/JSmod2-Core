using scpDataNetwork.network;

namespace scpDataNetwork.testSource
{
    public class TestPacket1 : DataPacket
    {
        public TestPacket1() :base(0x09)
        {
            
        }

        public override byte[] encode()
        {
            return dataObjectEncode("你好");
        }

        public override object decode(byte[] datas)
        {
            return dataObjectDecode(datas, typeof(string));
        }
    }
}