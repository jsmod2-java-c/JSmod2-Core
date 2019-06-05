package cn.jsmod2.scpslserver.network.event;

import cn.jsmod2.scpslserver.Register;
import cn.jsmod2.scpslserver.event.Event;
import cn.jsmod2.scpslserver.network.DataPacket;

import java.util.Map;
@Deprecated
public abstract class EventDataPacket extends DataPacket {

    private Map<Integer,Class<? extends Event>> events = Register.getInstance().getEvents();

    public Event event;

    public EventDataPacket(int id){
        super(id);
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public Event decode(byte[] bytes) {
        return dataObjectDecode(bytes,events.get(getId()));
    }

    @Override
    public byte[] encode() {
        return dataObjectEncode(event);
    }
}
