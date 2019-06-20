package cn.jsmod2.network.protocol.item;



public class SetItemInWorldPacket extends SetItemPacket {

    public SetItemInWorldPacket() {
        super(0x59);
    }

    private static final String INWORLD = "inWorld";

    public boolean setInWorld;

    @Override
    public void send() {
        requester.with(INWORLD,setInWorld)
                .end(playerName)
                .to();
    }
}
