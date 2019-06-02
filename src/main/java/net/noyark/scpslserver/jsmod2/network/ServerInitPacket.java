package net.noyark.scpslserver.jsmod2.network;

import net.noyark.scpslserver.jsmod2.Smod2Server;

/**
 * 用于初始化服务端信息的数据包
 * @author magiclu550
 */

public class ServerInitPacket extends DataPacket{


    public ServerInitPacket(){
        super(0x00);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Smod2Server decode(byte[] bytes) {
        Smod2Server o = null;
        try {
            o = dataObjectDecode(bytes, Smod2Server.class);
        }catch(Exception e){}
        return o;
    }
}
