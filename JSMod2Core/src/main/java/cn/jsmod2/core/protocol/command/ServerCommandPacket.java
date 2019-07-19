/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.protocol.command;


import cn.jsmod2.core.protocol.DataPacket;



public class ServerCommandPacket<T extends AbstractServerVO> extends DataPacket {


    private Class<T> type;
    public ServerCommandPacket(Class<T> type){
        this();
        this.type = type;
    }

    public ServerCommandPacket() {
        super(0x55);
    }

    @Override
    public T decode(byte[] bytes) {
        return dataObjectDecode(bytes, type);
    }
}
