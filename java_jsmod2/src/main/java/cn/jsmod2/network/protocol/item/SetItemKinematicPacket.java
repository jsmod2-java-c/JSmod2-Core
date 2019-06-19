package cn.jsmod2.network.protocol.item;



public class SetItemKinematicPacket extends SetItemPacket {


    public SetItemKinematicPacket() {
        super(0x5a);
    }

    private static final String KINEMATIC = "kinematic";

    public boolean kinematic;

    @Override
    public void send() {
        requester.with(KINEMATIC,kinematic)
                .end(playerName)
                .to(this);
    }
}
