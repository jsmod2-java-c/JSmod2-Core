package cn.jsmod2.network.protocol.event.newstream;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.protocol.Requester;
import cn.jsmod2.core.protocol.SetPacket;
/**
 * jsmod2中最新的一款数据包设计，在Proxy端通过反射实现，统一使用id 180
 * name为属性名，value为值,在后期的api中替代传统发包，但不代表它一定可以在任何地方使用
 * @author MagicLu550
 */
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
        if(value instanceof ApiId){
            requester.with("apiId","true");
        }
        requester.with("type","event").with("field",name).with(name,value).to();
    }
}
