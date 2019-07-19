/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.network;

import cn.jsmod2.api.server.Smod2Server;
import cn.jsmod2.core.protocol.DataPacket;

/**
 * 用于初始化服务端信息的数据包
 * @author magiclu550
 */

public class ServerInitPacket extends DataPacket {

    public static final int ID = 0x00;


    public ServerInitPacket(){
        super(0x00);
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }

    @Override
    public Smod2Server decode(byte[] bytes) {
        Smod2Server o = null;
        try {
            o = dataObjectDecode(bytes, Smod2Server.class);
        }catch(Exception e){}
        return o;
    }
}
