package cn.jsmod2.network.protocol.map.map;


public class DetonateWarheadMapPacket extends SetMapPacket {

    public static final int ID = 149;


    public DetonateWarheadMapPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with(DO,"detonateWarhead").to();
    }
}
