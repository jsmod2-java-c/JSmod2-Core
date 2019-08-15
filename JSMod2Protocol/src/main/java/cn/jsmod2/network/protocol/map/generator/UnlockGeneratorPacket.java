package cn.jsmod2.network.protocol.map.generator;

public class UnlockGeneratorPacket extends SetGeneratorPacket {

    public static final int ID = 131;

    public UnlockGeneratorPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with(DO,"unlock").to();
    }
}
