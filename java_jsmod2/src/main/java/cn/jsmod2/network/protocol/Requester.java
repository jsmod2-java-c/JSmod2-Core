package cn.jsmod2.network.protocol;

import cn.jsmod2.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于发送修改请求
 * Server.getRequester().with("","").with("","").end("").to(Packet.class).reset();
 */

public class Requester {

    private Server _sender;

    private String _end = "";

    private Map<String,Object> _map;

    public Requester(Server sender){
        _sender = sender;
        _map = new HashMap<>();
    }

    public Requester with(String key,Object value){
        _map.put(key,value);
        return this;
    }

    public Requester end(String end){
        _end = end;
        return this;
    }

    public Requester to(SetPacket packet){
        try{
            packet._infor_map = _map;
            packet._end = _end;
            _sender.sendPacket(packet);
        }catch (Exception e){
            e.printStackTrace();
        }

        return this;
    }

    public void reset(){
        _map.clear();
    }


}
