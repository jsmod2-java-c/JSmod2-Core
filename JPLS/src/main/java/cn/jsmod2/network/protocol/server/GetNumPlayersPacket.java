package cn.jsmod2.network.protocol.server;

public class GetNumPlayersPacket extends GetServerPacket {

    public static final int ID = 0x62;

    public GetNumPlayersPacket() {
        super(ID, GetRoundPacket.class);
    }

    @Override
    public Integer send() {
        return (Integer)(requester.with("field","numPlayers").get().get());
    }
}
