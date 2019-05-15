package net.noyark.scpslserver.jsmod2.network;

/**
 * @author magiclu550
 */

public class AdminQueryPacket extends DataPacket {

    public AdminQueryPacket(){
        super(0x01);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Object decode(byte[] bytes) {
        return null;
    }
}
