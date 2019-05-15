package net.noyark.scpslserver.jsmod2.network;

/**
 * 用于初始化服务端信息的数据包
 * @author magiclu550
 */

public class ServerInitPacket extends DataPacket{

    public ServerInitPacket(){
        super(0x0);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Object decode() {
        return null;
    }
}
