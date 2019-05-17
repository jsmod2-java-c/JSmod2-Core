package net.noyark.scpslserver.jsmod2.event.packet;

import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.network.DataPacket;

public class PacketEvent extends Event {

    private DataPacket packet;

    public PacketEvent(DataPacket packet){
        this.packet = packet;
    }

    public PacketEvent(){

    }

    public DataPacket getPacket() {
        return packet;
    }

    public void setPacket(DataPacket packet) {
        this.packet = packet;
    }
}
