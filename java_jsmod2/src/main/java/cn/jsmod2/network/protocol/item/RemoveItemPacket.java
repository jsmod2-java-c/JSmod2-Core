package cn.jsmod2.network.protocol.item;


public class RemoveItemPacket extends SetItemPacket {
    public RemoveItemPacket() {
        super(0x58);
    }

    @Override
    public void send() {
        requester.with(ID,getId())
                .with(DO,"remove")
                .end(playerName)
                .to(this);
    }
}
