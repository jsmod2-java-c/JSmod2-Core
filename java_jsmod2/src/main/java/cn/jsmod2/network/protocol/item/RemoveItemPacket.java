package cn.jsmod2.network.protocol.item;


public class RemoveItemPacket extends SetItemPacket {

    @Override
    public void send() {
        server.getRequester()
                .with(DO,"remove")
                .end(playerName)
                .to(this);
    }
}
