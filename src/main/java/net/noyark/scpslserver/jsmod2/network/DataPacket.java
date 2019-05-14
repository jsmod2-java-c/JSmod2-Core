package net.noyark.scpslserver.jsmod2.network;


public abstract class DataPacket extends BinaryStream{

    public DataPacket(int id) {
        super(id);
    }

    public abstract byte[] encode();

    public abstract Object decode();
}
