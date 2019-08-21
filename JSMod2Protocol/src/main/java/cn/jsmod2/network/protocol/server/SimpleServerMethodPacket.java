package cn.jsmod2.network.protocol.server;

import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.SimpleMethodStream;

public class SimpleServerMethodPacket extends SimpleMethodStream {

    public static final int ID = 197;



    public SimpleServerMethodPacket(Class<?> type) {
        super(ID, type);
    }

}
