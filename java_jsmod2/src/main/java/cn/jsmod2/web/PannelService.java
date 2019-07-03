package cn.jsmod2.web;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.ex.ServerRuntimeException;
import cn.jsmod2.core.protocol.DataPacket;
import org.springframework.stereotype.Service;

@Service
public class PannelService {

    //比如获取服务器最大人数
    //0x64-{"id":0x64,"type":"server","field":"maxPlayers"}
    public Object api(String json){
        return Server.getSender().getServer().sendPacketGetResult(new DataPacket() {
            @Override
            public byte[] encode() {
                try {
                    return dataJsonEncode(json);
                }catch (Exception e){
                    throw new ServerRuntimeException(e);
                }
            }
        });
    }


}
