package cn.jsmod2.core.protocol;

import java.util.List;

public class MultiAdminCommand {

    public static List sendCommand(String name, String[] args){
        CommandSendPacket packet = new CommandSendPacket();
        packet.args = args;
        packet.command = name;
        return packet.send();
    }
}
