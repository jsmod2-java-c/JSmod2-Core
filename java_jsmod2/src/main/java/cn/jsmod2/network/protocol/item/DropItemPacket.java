package cn.jsmod2.network.protocol.item;




public class DropItemPacket extends SetItemPacket {

    public DropItemPacket() {
        super(0x57);
    }

    @Override
    public void send() {
        requester.with(ID,getId())
                .with(DO,"drop")
                .end(playerName)
                .to(this);
    }
}
