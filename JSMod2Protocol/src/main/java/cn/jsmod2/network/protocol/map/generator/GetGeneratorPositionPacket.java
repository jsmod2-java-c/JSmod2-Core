package cn.jsmod2.network.protocol.map.generator;

import cn.jsmod2.core.math.Vector;

//137
public class GetGeneratorPositionPacket extends GetGeneratorPacket{

    public static final int ID = 137;

    public GetGeneratorPositionPacket() {
        super(ID, Vector.class);
    }

    @Override
    public Vector send() {
        return (Vector) requester.with("field","position").get().get();
    }
}
