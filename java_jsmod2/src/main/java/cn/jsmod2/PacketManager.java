
/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2;

import cn.jsmod2.api.server.Smod2Server;
import cn.jsmod2.event.packet.ServerPacketEvent;
import cn.jsmod2.ex.EventException;
import cn.jsmod2.network.command.PlayerCommandPacket;
import cn.jsmod2.network.command.ServerCommandPacket;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.event.Event;
import cn.jsmod2.network.EventBinaryStream;
import cn.jsmod2.network.command.PlayerVO;
import cn.jsmod2.network.command.ServerVO;

import java.util.Properties;

/**
 * 清算的发包：
 *  指令注册的发包
 *  c#端有一个指令处理器CommandHandler负责中转jsmod2的指令
 *  物品设置的发包
 *  传输SetPacket,里面包含json值
 *  {"id":"包的id","type":"类型","修改值字段",{对象}}~尾部请求(一般附加玩家的名称)
 *  设置config的发包
 *  和SetPacket基本一样
 * 接受包：
 *  事件接受包 - 带附加请求
 *  字段链注入规则:主事件对象的字段名-该字段指向对象中的字段名-...-最终注入的字段名
 *  如xxEvent的字段
 *  A a;
 *  A的字段
 *  int b;
 *  注入xxEvent的b就是
 *  a-b:1即可
 *  {主事件对象},注入字段链:{}
 *  指令调用接受包 - 带权限管理
 *  {VO对象的内容}
 */
public class PacketManager {

    private static PacketManager manager;

    static {
        manager = new PacketManager();
    }


    private PacketManager(){
    }


    /**
     * 处理包的逻辑写在这里
     */
    public void manageMethod(String message,int id){
        try{
            Properties properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
            byte[] bytes = message.getBytes(properties.getProperty("encode"));//通过utf-8形式获取byte字节数组
            if(id == Register.FRIST_EVENT||(Register.SECOND_START_EVENT<=id&&id<Register.MAX_EVENT_ID)){
                callEventByPacket(id,bytes);
            }
            /* 执行指令的部分 */
            if(id == Register.SERVER_COMMAND){
                ServerCommandPacket serverCommandPacket = new ServerCommandPacket();
                ServerVO vo = serverCommandPacket.decode(bytes);
                Smod2Server sender = vo.getServer();
                String[] args = vo.getArgs();
                String commandName = vo.getCommandName();
                Console.getConsole().runConsoleCommand(commandName,args);
                Server.getSender().getServer().getSmod2Server().updateServer(sender);
            }
            if(id == Register.PLAYER_COMMAND){
                PlayerCommandPacket playerCommandPacket = new PlayerCommandPacket();
                PlayerVO vo = playerCommandPacket.decode(bytes);
                Player player = vo.getPlayer();
                String commandName = vo.getCommandName();
                String[] args = vo.getArgs();
                Server.getSender().getServer().getPluginManager().executeCommand(commandName,args,player);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 通过数据包调用event
     * @param id
     * @param bytes
     */

    public void callEventByPacket(int id, byte[] bytes){

        EventBinaryStream stream = new EventBinaryStream();

        Class<? extends Event> eventClass = Register.getInstance().getEvents().get(id);
        if(eventClass != null){
            Event event = stream.encode(eventClass,bytes);
            Server.getSender().getServer().getPluginManager().callEvent(event);
        }else{
            throw new EventException("No such type of events");
        }
    }

    public static PacketManager getManager() {
        return manager;
    }
}
