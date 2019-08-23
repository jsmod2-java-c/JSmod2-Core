package cn.jsmod2.network.protocol.item;


public class RemoveItemPacket extends SetItemPacket {

    public static final int ID = 0x58;
    public RemoveItemPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester
                .with(DO,"remove")
                .to();
    }
}
