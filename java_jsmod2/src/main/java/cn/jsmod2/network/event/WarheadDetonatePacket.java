package cn.jsmod2.network.event;

@Deprecated
public class WarheadDetonatePacket extends EventDataPacket {

    public WarheadDetonatePacket(int id) {
        super(0x0c);
    }
}
