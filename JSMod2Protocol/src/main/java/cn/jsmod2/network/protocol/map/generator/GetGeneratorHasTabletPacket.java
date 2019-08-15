package cn.jsmod2.network.protocol.map.generator;

//135
public class GetGeneratorHasTabletPacket extends GetGeneratorPacket{

    public static final int ID = 135;

    public GetGeneratorHasTabletPacket() {
        super(ID,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)requester.with("field","hasTablet").get().get();
    }
}
