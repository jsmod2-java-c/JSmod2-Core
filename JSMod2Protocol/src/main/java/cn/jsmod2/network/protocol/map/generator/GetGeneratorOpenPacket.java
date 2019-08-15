package cn.jsmod2.network.protocol.map.generator;

public class GetGeneratorOpenPacket extends GetGeneratorPacket{

    public static final int ID = 132;

    public GetGeneratorOpenPacket() {
        super(ID, Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean) requester.with("field","open").get().get();
    }
}
