/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.protocol;


/**
 * Jsmod2服务器交互协议 V4
 *
 * 数据类型分为ControlPacket，EventPacket，CommandPacket
 *
 * EventPacket整个内容是一个Event对象
 * CommandPacket内部是一个CommandVO对象
 * 	CommandVO分为ServerVO和PlayerVO
 * 	他们存储着Command名称和参数数组，以及CommandSender对象
 * ControlPacket是控制对象数据的数据包
 * 	分为SetPacket和GetPacket
 * 	SetPacket一定会修改对象的属性或者行为
 * 	GetPacket不一定修改对象的属性或者行为，但一定会有相应的返回值
 * 	SetPacket和GetPacket底层都是Requester实现的
 * 		Requester是数据发送对象，会将数据整合封装成json，发给ProxyHandler
 * 	SetPacket和GetPacket的头请求
 * 		“id”:”包id”
 * 	SetPacket和GetPacket的type
 * 		“type”:”操作类名的首字母小写”
 * 		如操作Item对象，那么type为item
 * 	SetPacket的字段
 * 		当引导做一个事情而不是修改属性
 * 		“do”:”方法名”
 * 		如item对象调用remove方法实现删除动作，那么就是”do”:”remove”
 * 	GetPacket的字段
 * 		“field”:”获取字段” 这里就是去掉get的，首字母小写的字段名
 * 		如Item的getKinematic方法获取kinematic，那就是“field”:”kinematic”
 * 注入字段:
 * 	如用于注入到如Item的使用玩家名称来定位Item,需要额外注入，采用|分隔符
 * 	如XXXEvent,里面有个playerName字段要额外注入
 * 	可以这样
 *        {XXXEvent对象}|playerName:xxx
 * 	可以实现注入
 */
public abstract class DataPacket extends BinaryStream {



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
