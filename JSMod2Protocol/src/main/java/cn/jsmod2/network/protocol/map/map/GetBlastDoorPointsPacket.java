package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.math.Vector;

import java.util.List;

public class GetBlastDoorPointsPacket extends GetMapPacket {

    public Role role;

    public static final int ID = 152;

    public GetBlastDoorPointsPacket(){
        super(ID, Vector.class);
    }

    @Override
    public List send() {
        return requester.with("role",role).get().getArray();
    }
}
