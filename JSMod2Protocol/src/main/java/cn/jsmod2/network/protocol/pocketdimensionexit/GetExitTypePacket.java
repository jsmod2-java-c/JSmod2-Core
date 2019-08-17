package cn.jsmod2.network.protocol.pocketdimensionexit;

import cn.jsmod2.api.map.PocketDimensionExitType;
import cn.jsmod2.core.protocol.GetPacket;

//177
public class GetExitTypePacket extends GetPacket {

    public static final int ID = 177;

    public GetExitTypePacket(){
        super(ID, PocketDimensionExitType.class);
    }

    @Override
    public Object send() {
        return requester.with("field","exitType").get().get();
    }
}
