package cn.jsmod2.scpslserver.event.packet;

import cn.jsmod2.scpslserver.network.DataPacket;

public class ServerPacketEvent extends PacketEvent {

    public ServerPacketEvent(DataPacket packet){
        super(packet);
    }

}
