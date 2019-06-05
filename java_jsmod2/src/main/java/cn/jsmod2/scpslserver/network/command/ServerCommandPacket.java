package cn.jsmod2.scpslserver.network.command;

import cn.jsmod2.scpslserver.network.DataPacket;

public class ServerCommandPacket extends DataPacket {

    @Override
    public ServerVO decode(byte[] bytes) {
        return dataObjectDecode(bytes,ServerVO.class);
    }
}
