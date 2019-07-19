package cn.jsmod2.network.protocol.server;

public class GetMaxPlayersPacket extends GetServerPacket{

    public static final int ID = 0x64;

    public GetMaxPlayersPacket() {
        super(ID, Integer.class);
    }

    @Override
    public Integer send() {
        return (Integer) requester.with("field","maxPlayers").get().get();
    }
}
