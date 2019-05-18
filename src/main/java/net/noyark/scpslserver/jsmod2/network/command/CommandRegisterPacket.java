package net.noyark.scpslserver.jsmod2.network.command;

import net.noyark.scpslserver.jsmod2.command.NativeCommand;
import net.noyark.scpslserver.jsmod2.network.DataPacket;

public class CommandRegisterPacket extends DataPacket {

    public NativeCommand command;


    @Override
    public byte[] encode() {
        return dataObjectEncode(command);
    }

}
