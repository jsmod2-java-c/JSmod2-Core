package cn.jsmod2.network.protocol.server;

public class GetIpAddressPacket extends GetServerPacket {

    public static final int ID = 0x60;

    public GetIpAddressPacket() {
        super(ID, String.class);
    }

    @Override
    public String send() {
        return (String)( requester.with("field","ipAddress").get().get());
    }
}
