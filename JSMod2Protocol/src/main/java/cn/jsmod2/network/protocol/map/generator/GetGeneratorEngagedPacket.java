package cn.jsmod2.network.protocol.map.generator;

//134
public class GetGeneratorEngagedPacket extends GetGeneratorPacket{

    public static final int ID = 134;

    public GetGeneratorEngagedPacket() {
        super(ID,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean)requester.with("field","engaged").get().get();
    }
}
