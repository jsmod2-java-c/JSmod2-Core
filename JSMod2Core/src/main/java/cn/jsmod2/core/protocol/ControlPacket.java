package cn.jsmod2.core.protocol;

import cn.jsmod2.core.Server;

import java.util.Map;

public class ControlPacket extends DataPacket {

    public String playerName;

    public ControlPacket(int id) {
        super(id);
    }

    public Map<String,Object> _infor_map;

    public String _end;

    protected final Server server = Server.getSender().getServer();

    protected final Requester requester = server.getRequester(this)
            .with(ID,getId())
            .with("player",playerName);



    @Override
    public byte[] encode() {
        return dataObjectEncodeWithEnd(_infor_map,_end);
    }



}
