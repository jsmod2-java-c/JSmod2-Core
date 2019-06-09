package cn.jsmod2.network.protocol.item;

import cn.jsmod2.network.DataPacket;

public class DropItemPacket extends DataPacket {

    @Override
    public byte[] encode() {
        return dataObjectEncode("drop");
    }
}
