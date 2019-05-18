package net.noyark.scpslserver.jsmod2.network.command;

import net.noyark.scpslserver.jsmod2.network.DataPacket;

public class ServerCommandPacket extends DataPacket {

    @Override
    public ServerVO decode(byte[] bytes) {
        return dataObjectDecode(bytes,ServerVO.class);
    }
}
