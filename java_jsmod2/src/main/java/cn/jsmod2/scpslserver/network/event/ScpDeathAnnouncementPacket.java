package cn.jsmod2.scpslserver.network.event;


@Deprecated
public class ScpDeathAnnouncementPacket extends EventDataPacket{

    public ScpDeathAnnouncementPacket() {
        super(0x09);
    }
}
