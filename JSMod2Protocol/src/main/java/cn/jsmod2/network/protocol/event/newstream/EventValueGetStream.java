package cn.jsmod2.network.protocol.event.newstream;

import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Requester;
import cn.jsmod2.core.protocol.Response;

/**
 * jsmod2中最新的一款数据包设计，在Proxy端通过反射实现，统一使用id 180
 * name为属性名，在后期的api中替代传统发包，但不代表它一定可以在任何地方使用
 * @author MagicLu550
 */
public class EventValueGetStream extends GetPacket {

    public String name;

    public int getType;

    public EventValueGetStream(int id,Class<?> type){
        super(id,type);
    }

    public EventValueGetStream(Class<?> type){
        super(180,type);
    }
    public Requester getRequester(){
        return requester;
    }

    @Override
    public Object send() {
        Response response = requester.with("type","event").with("field",name).get();
        if(getType == GetTypes.GET)return response.get();
        if(getType == GetTypes.GET_ARRAY)return response.getArray();
        if(getType == GetTypes.GET_PROTOCOL_ARRAY_WITH_LIST_IN)return response.getProtocolArray(true);
        if(getType == GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN)return response.getProtocolArray(false);
        return response.get();
    }
}
