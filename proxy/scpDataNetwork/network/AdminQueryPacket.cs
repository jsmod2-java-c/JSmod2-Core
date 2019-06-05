using System;
using System.Collections.Generic;
using Smod2.Events;

namespace scpDataNetwork.network
{
    public class AdminQueryPacket : DataPacket
    {
        
        private AdminQueryEvent e;

        public AdminQueryPacket() : base(0x01)
        {
            
        }

        public override byte[] encode()
        {
            return dataObjectEncode(e);
        }

        public override object decode(byte[] datas)
        {
            return dataObjectDecode(datas,typeof(AdminQueryEvent));
        }
    }
}