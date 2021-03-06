package cn.jsmod2.core.protocol;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.interapi.network.IRequester;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.core.utils.Future;
import cn.jsmod2.core.utils.Utils;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类用于发送修改请求
 * Server.getRequester(packet)
 *                  .with("","")
 *                  .with("","")
 *                  .end("")
 *                  .to();
 * @author magiclu550
 */

public class Requester implements IRequester {

    private Server _sender;

    private String _end = "";

    private Map<String,Object> _map;

    private ControlPacket _packet;

    public Requester(Server sender, ControlPacket packet){
        _sender = sender;
        _map = new HashMap<>();
        _packet = packet;
    }

    public Requester with(String key,Object value){
        if(value == null)return this;
        _map.put(key,value instanceof ApiId?((ApiId) value).getApiId():value instanceof Enum?"'"+value+"'":value.getClass().isArray()||value instanceof List?JSON.toJSONString(value):value.toString());
        return this;
    }

    public Requester withObject(String key,Object value){
        _map.put(key,value);
        return this;
    }

    public Requester end(String end){
        _end = end;
        return this;
    }

    public Requester to(){
        try{
            _packet._infor_map = _map;
            _packet._end = _end;
            _sender.sendPacket(_packet);
            this.reset();
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public Response get(){
        try{
            _packet._infor_map = _map;
            _packet._end = _end;
            Future future =  _sender.sendPacketGetResult(_packet);
            Response response = new Response();
            if(_packet instanceof GetPacket){
                response.future = future;
                response.packet = (GetPacket) _packet;
            }
            return response;
        }catch (Exception e){
            Utils.printException(e);
        }
        return null;
    }

    public String getString(){
        return _packet.getId()+"-"+JSON.toJSONString(_map)+"~"+_end;
    }

    public void reset(){
        _map.clear();
    }


}
