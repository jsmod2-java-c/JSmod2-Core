package cn.jsmod2.web;

import cn.jsmod2.core.RegisterTemplate;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.ex.ServerRuntimeException;
import cn.jsmod2.core.protocol.DataPacket;
import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.Response;
import cn.jsmod2.core.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class PanelService {

    //比如获取服务器最大人数
    //0x64-{"id":0x64,"type":"server","field":"maxPlayers"}
    public Object api(String json){
        int id = Utils.getResponsePacketId(new String(Base64.getEncoder().encode(json.getBytes())));
        Response response = new Response();
        response.future = Server.getSender().getServer().sendPacketGetResult(new DataPacket(id) {

            @Override
            public byte[] encode() {
                try {
                    return dataJsonEncode(json);
                }catch (Exception e){
                    throw new ServerRuntimeException(e);
                }
            }
        });

        Map<Integer,Class<? extends DataPacket>> map = new HashMap<>();

        for(RegisterTemplate template:Server.getSender().getServer().getRegisters()){
            map.putAll(template.getGetPackets());
        }
        try{
            DataPacket dataPacket = map.get(id).newInstance();
            if(dataPacket instanceof GetPacket){
                response.packet = ((GetPacket) dataPacket);
            }
            Map<String,Object> things = new HashMap<>();
            things.put("json",response.get());
            return things;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
