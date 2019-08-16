package cn.jsmod2.network.protocol.map.map;

import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.core.protocol.GetPacket;

public class GetBlastDoorPointsPacket extends GetPacket {

    public Role role;

    public static final int ID = 152;

    public GetBlastDoorPointsPacket(){
        super(ID, Vector.class);
    }

    @Override
    public Object send() {
        return requester.with("role",role);
    }
}
