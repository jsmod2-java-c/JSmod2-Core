package cn.jsmod2.network.protocol.pocketdimensionexit;

import cn.jsmod2.core.protocol.SetPacket;

public abstract class SetExitPacket extends SetPacket {

    public SetExitPacket(int id) {
        super(id);
        requester.with("type","pocketDimensionExit");
    }

}
