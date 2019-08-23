package cn.jsmod2.network.protocol.item;



public class SetItemKinematicPacket extends SetItemPacket {

    public static final int ID = 0x5a;

    public SetItemKinematicPacket() {
        super(ID);
    }

    private static final String KINEMATIC = "kinematic";

    public boolean kinematic;

    @Override
    public void send() {
        requester.with(KINEMATIC,kinematic)
                .to();
    }
}
