/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.protocol.command;

import cn.jsmod2.core.protocol.DataPacket;


public class PlayerCommandPacket<P extends AbstractPlayerVO> extends DataPacket {

    private Class<P> type;

    public PlayerCommandPacket(Class<P> type){
        this();
        this.type = type;
    }

    public PlayerCommandPacket() {
        super(0x56);
    }

    @Override
    public P decode(byte[] bytes) {
        return dataObjectDecode(bytes, type);
    }
}
