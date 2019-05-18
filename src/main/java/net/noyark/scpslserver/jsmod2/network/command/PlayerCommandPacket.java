package net.noyark.scpslserver.jsmod2.network.command;

import net.noyark.scpslserver.jsmod2.network.DataPacket;

public class PlayerCommandPacket extends DataPacket {

    @Override
    public PlayerVO decode(byte[] bytes) {
        return dataObjectDecode(bytes, PlayerVO.class);
    }
}
