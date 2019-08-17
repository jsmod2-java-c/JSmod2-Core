package cn.jsmod2.network.protocol.event.newstream;

import cn.jsmod2.core.protocol.Requester;
import cn.jsmod2.core.protocol.SetPacket;

public class EventValueSetStream extends SetPacket {

    public String name;

    public Object value;


    public EventValueSetStream(int id) {
        super(id);
    }

    public EventValueSetStream(){
        super(181);
    }

    public Requester getRequester(){
        return requester;
    }


    @Override
    public void send() {
        requester.with("type","event").with("field",name).with(name,value).to();
    }
}
