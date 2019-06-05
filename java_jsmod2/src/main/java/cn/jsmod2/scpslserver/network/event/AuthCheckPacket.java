package cn.jsmod2.scpslserver.network.event;

@Deprecated
public class AuthCheckPacket extends EventDataPacket {


    public AuthCheckPacket() {
        super(0x03);
    }


}
