package cn.jsmod2.network.event;

@Deprecated
public class BanPacket extends EventDataPacket {

    public BanPacket() {
        super(0x04);
    }

}