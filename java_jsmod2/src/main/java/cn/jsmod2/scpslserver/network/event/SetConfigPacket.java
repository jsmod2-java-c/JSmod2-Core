package cn.jsmod2.scpslserver.network.event;




@Deprecated
public class SetConfigPacket extends EventDataPacket {

    public SetConfigPacket() {
        super(0x05);
    }
}
