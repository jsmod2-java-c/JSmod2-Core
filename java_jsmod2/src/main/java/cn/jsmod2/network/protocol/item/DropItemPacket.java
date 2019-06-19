package cn.jsmod2.network.protocol.item;




public class DropItemPacket extends SetItemPacket {


    @Override
    public void send() {
        server.getRequester()
                .with(DO,"drop")
                .end(playerName)
                .to(this);
    }
}
