/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.protocol;

import cn.jsmod2.core.command.NativeCommand;

/**
 * 内置数据包，用于处理远端发来的指令注册数据包
 *
 * @author magiclu550
 */

public class CommandRegisterPacket extends DataPacket {

    public static final int ID = 0x53;


    public CommandRegisterPacket() {
        super(0x53);
    }

    public NativeCommand command;


    @Override
    public byte[] encode() {
        return dataObjectEncode(command);
    }

}
