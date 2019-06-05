package cn.jsmod2.scpslserver.network.command;

import cn.jsmod2.scpslserver.network.DataPacket;

public class PlayerCommandPacket extends DataPacket {

    @Override
    public PlayerVO decode(byte[] bytes) {
        return dataObjectDecode(bytes, PlayerVO.class);
    }
}
