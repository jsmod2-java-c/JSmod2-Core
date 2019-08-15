package cn.jsmod2.network.protocol.map.generator;

//139
public class GetGeneratorStartTimePacket extends GetGeneratorPacket{

    public static final int ID = 139;

    public GetGeneratorStartTimePacket() {
        super(ID,Float.class);
    }

    @Override
    public Float send() {
        return (Float) requester.with("field","startTime").get().get();
    }
}
