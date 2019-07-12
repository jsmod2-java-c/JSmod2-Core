package cn.jsmod2.network.protocol.server;

public class SetMaxPlayersPacket extends SetServerPacket {

    public static final int ID = 0x65;
    public int maxPlayers;

    public SetMaxPlayersPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("maxPlayer",maxPlayers+"").to();
    }
}
