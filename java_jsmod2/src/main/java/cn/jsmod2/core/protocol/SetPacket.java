package cn.jsmod2.core.protocol;

import cn.jsmod2.core.Server;


import java.util.Map;

/**
 * 做一个事情:do-方法全小写名
 * 修改值:字段名-值
 * 封装了设置数据包的操作
 */
public class SetPacket extends DataPacket {

    public SetPacket(int id) {
        super(id);
    }

    protected static final String DO = "do";

    public Map<String,Object> _infor_map;

    public String _end;


    protected final Server server = Server.getSender().getServer();

    protected final Requester requester = server.getRequester(this).with(ID,getId());

    @Override
    public byte[] encode() {
        return dataObjectEncodeWithEnd(_infor_map,_end);
    }

    public void send(){}

}
