package net.noyark.scpslserver.jsmod2.network;



/**
 *
 * 数据包的分类
 * 服务器初始化数据包 1.只有接收包，初始化jsmod2服务器
 *                    2.通过服务器事件->更新服务器数据
 * 关闭服务器数据包 发送0x02字，远端关闭服务器
 * 事件数据包 只有接收包，远程服务端根据事件发来数据包[直接通过message解析,event包作废]
 * 设置数据包 只有发包，将修改数据传输过去，并调用相应方法
 * 指令数据包，触发指令->发送指令事件数据包->解析为指令->调用相应的方法->发回数据
 * @author magiclu550
 *
 */

public abstract class DataPacket extends BinaryStream{



    public DataPacket(int id) {
        super(id);
    }

    public DataPacket(){
        super();
    }

    public byte[] encode(){
        return null;
    }

    public Object decode(byte[] bytes){
        return null;
    }

}
