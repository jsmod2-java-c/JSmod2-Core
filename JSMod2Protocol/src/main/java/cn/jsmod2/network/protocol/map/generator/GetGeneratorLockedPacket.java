package cn.jsmod2.network.protocol.map.generator;

//136
public class GetGeneratorLockedPacket extends GetGeneratorPacket{

    public static final int ID = 136;

    public GetGeneratorLockedPacket() {
        super(ID,Boolean.class);
    }

    @Override
    public Boolean send() {
        return (Boolean) requester.with("field","locked").get().get();
    }
}
