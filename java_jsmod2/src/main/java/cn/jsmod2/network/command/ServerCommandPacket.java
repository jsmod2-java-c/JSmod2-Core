/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.network.command;

import cn.jsmod2.core.protocol.DataPacket;

public class ServerCommandPacket extends DataPacket {

    public ServerCommandPacket() {
        super(0x55);
    }

    @Override
    public ServerVO decode(byte[] bytes) {
        return dataObjectDecode(bytes,ServerVO.class);
    }
}
