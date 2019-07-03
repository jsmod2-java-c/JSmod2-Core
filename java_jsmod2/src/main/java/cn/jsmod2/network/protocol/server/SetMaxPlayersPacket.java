package cn.jsmod2.network.protocol.server;

public class SetMaxPlayersPacket extends SetServerPacket {

    public int maxPlayers;

    public SetMaxPlayersPacket() {
        super(0x65);
    }

    @Override
    public void send() {
        requester.with("maxPlayer",maxPlayers+"").to();
    }
}
