package cn.jsmod2.network.protocol;

import cn.jsmod2.Server;
import cn.jsmod2.network.DataPacket;

import java.util.Map;

public class SetPacket extends DataPacket {

    protected static final String DO = "do";

    public Map<String,Object> _infor_map;

    protected Server server = Server.getSender().getServer();
    public void send(){}

}
