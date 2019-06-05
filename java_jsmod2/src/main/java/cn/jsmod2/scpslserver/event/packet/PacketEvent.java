package cn.jsmod2.scpslserver.event.packet;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.event.Event;
import cn.jsmod2.scpslserver.network.DataPacket;

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


    /** java-bean */
    @UseForServerInit
    public void setPacket(DataPacket packet) {
        this.packet = packet;
    }
}
