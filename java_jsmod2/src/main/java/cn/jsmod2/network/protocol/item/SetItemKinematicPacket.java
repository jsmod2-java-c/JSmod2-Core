package cn.jsmod2.network.protocol.item;



public class SetItemKinematicPacket extends SetItemPacket {


    private static final String KINEMATIC = "kinematic";

    public boolean kinematic;

    @Override
    public void send() {
        server.getRequester()
                .with(KINEMATIC,kinematic)
                .end(playerName)
                .to(this);
    }
}
