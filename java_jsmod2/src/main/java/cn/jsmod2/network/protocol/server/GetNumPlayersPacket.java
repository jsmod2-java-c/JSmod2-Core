package cn.jsmod2.network.protocol.server;

public class GetNumPlayersPacket extends GetServerPacket {

    public GetNumPlayersPacket() {
        super(0x62, GetRoundPacket.class);
    }

    @Override
    public Integer send() {
        return (Integer)(requester.with("field","numPlayers").get().get());
    }
}
