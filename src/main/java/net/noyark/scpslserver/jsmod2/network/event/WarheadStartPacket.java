package net.noyark.scpslserver.jsmod2.network.event;

@Deprecated
public class WarheadStartPacket extends EventDataPacket {

    public WarheadStartPacket(int id) {
        super(0x0e);
    }
}
