package cn.jsmod2.core.protocol;

import cn.jsmod2.core.protocol.GetPacket;
import cn.jsmod2.core.protocol.SetPacket;
import cn.jsmod2.core.utils.Utils;

import java.util.List;

/**
 * 用于发送multiAdmin的命令
 */
public class CommandSendPacket extends GetPacket {

    public String command;

    public String[] args;

    public static final int ID = 107;

    public CommandSendPacket() {
        super(ID,String.class);
    }

    @Override
    public List send() {
        return requester.with("type","command").with("name",command).with("args", Utils.arraysToString(args))
        .get().getArray();
    }



}
