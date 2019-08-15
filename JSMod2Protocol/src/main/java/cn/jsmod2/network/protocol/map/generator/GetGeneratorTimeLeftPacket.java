package cn.jsmod2.network.protocol.map.generator;

//140
public class GetGeneratorTimeLeftPacket extends GetGeneratorPacket{

    public static final int ID = 140;

    public GetGeneratorTimeLeftPacket() {
        super(ID,Float.class);
    }

    @Override
    public Float send() {
        return (Float)requester.with("field","timeLeft").get().get();
    }
}
