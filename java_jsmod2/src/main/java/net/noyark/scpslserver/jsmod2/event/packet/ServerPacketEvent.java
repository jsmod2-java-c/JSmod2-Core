package net.noyark.scpslserver.jsmod2.event.packet;

import net.noyark.scpslserver.jsmod2.network.DataPacket;

public class ServerPacketEvent extends PacketEvent {

    public ServerPacketEvent(DataPacket packet){
        super(packet);
    }

}
