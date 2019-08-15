package cn.jsmod2.network.protocol.map.generator;

//141
public class SetGeneratorHasTabletPacket extends SetGeneratorPacket{

    public static final int ID = 141;

    public boolean hasTablet;

    public SetGeneratorHasTabletPacket() {
        super(ID);
    }

    @Override
    public void send() {
        requester.with("hasTablet",hasTablet).to();
    }
}
