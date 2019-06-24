package cn.jsmod2.core.protocol;

import cn.jsmod2.core.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于发送修改请求
 * Server.getRequester()
 *                  .with("","")
 *                  .with("","")
 *                  .end("")
 *                  .to(Packet);
 */

public class Requester {

    private Server _sender;

    private String _end = "";

    private Map<String,Object> _map;

    private SetPacket _packet;

    public Requester(Server sender, SetPacket packet){
        _sender = sender;
        _map = new HashMap<>();
        _packet = packet;
    }

    public Requester with(String key,Object value){
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

    public void reset(){
        _map.clear();
    }


}
