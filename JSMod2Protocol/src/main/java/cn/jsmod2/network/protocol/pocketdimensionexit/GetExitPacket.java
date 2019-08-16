package cn.jsmod2.network.protocol.pocketdimensionexit;

import cn.jsmod2.core.protocol.GetPacket;

public abstract class GetExitPacket extends GetPacket {

    public GetExitPacket(int id, Class<?> type) {
        super(id, type);
    }
}
