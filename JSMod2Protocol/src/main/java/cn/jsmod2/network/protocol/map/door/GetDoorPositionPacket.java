package cn.jsmod2.network.protocol.map.door;

import cn.jsmod2.core.math.Vector;

public class GetDoorPositionPacket extends GetDoorPacket {

    public static final int ID = 118;

    public GetDoorPositionPacket(){
        super(ID, Vector.class);
    }

    @Override
    public Vector send() {
        return (Vector)requester.with("field","position").get().get();
    }
}
