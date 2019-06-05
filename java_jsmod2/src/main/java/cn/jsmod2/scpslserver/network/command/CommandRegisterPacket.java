package cn.jsmod2.scpslserver.network.command;

import cn.jsmod2.scpslserver.command.NativeCommand;
import cn.jsmod2.scpslserver.network.DataPacket;

public class CommandRegisterPacket extends DataPacket {

    public NativeCommand command;


    @Override
    public byte[] encode() {
        return dataObjectEncode(command);
    }

}
