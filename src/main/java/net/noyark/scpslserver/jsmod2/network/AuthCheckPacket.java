package net.noyark.scpslserver.jsmod2.network;

public class AuthCheckPacket extends DataPacket{


    public AuthCheckPacket() {
        super(0x03);
    }

    @Override
    public byte[] encode() {
        return null;
    }

    @Override
    public Object decode(byte[] bytes) {
        return null;
    }
}
