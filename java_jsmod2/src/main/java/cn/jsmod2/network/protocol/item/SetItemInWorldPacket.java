package cn.jsmod2.network.protocol.item;



public class SetItemInWorldPacket extends SetItemPacket {

    private static final String INWORLD = "inworld";

    public boolean setInworld;

    @Override
    public void send() {
        server.getRequester()
                .with(INWORLD,setInworld)
                .end(playerName)
                .to(this);
    }
}
