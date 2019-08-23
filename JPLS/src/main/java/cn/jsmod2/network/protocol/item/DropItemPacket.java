package cn.jsmod2.network.protocol.item;




public class DropItemPacket extends SetItemPacket {

    public static final int ID = 0x57;

    public DropItemPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester
                .with(DO,"drop")
                .to();
    }
}
