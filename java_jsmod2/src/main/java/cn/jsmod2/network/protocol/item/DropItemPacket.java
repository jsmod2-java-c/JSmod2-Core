package cn.jsmod2.network.protocol.item;



import cn.jsmod2.network.protocol.SetPacket;

public class DropItemPacket extends SetPacket {

    public String playerName;

    @Override
    public void send() {
        server.getRequester()
                .with("info","drop")
                .end(playerName)
                .to(this);
    }
}
