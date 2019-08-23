package cn.jsmod2.network.protocol.map.generator;

//142
public class SetGeneratorOpenPacket extends SetGeneratorPacket {

    public boolean open;

    public static final int ID = 133;

    public SetGeneratorOpenPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("open",open).to();
    }
}
