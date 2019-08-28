
/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2;

import cn.jsmod2.core.*;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.core.protocol.command.CommandVO;
import cn.jsmod2.network.command.*;
import cn.jsmod2.api.player.Player;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
 *  PacketManager是本服务端默认的包处理器，进行的任务主要是指令执行,事件调用的功能
 * @author MagicLu550
 * @since 1.0.0
 */
public class PacketManager extends Manager {

    private static PacketManager manager;

    static {
        manager = new PacketManager();
    }


    private PacketManager(){
    }


    /**
     *  用于切面处理数据包信息，最终调用所需求的地方
     * @param message json字符串
     * @param id 数据包的id号
     * @param socket 此时接收到的Socket对象，作用是释放事件的执行,使得下一个事件进行
     *               执行完成后返回0xFF&1的值
     */
    public void manageMethod(String message, int id, Socket socket){
        try{
            Properties properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
            byte[] bytes = message.getBytes(properties.getProperty("encode"));//通过utf-8形式获取byte字节数组
            Map<Integer, Class<? extends Event>> events = new HashMap<>();
            for(RegisterTemplate template:Server.getSender().getServer().getRegisters()){
                events.putAll(template.getEvents());
            }
            if(events.containsKey(id)){
                //System.out.println(new String(Base64.getDecoder().decode(new String(bytes))));
                callEventByPacket(id,bytes);
                //ServerLogger.getLogger().multiInfo(getClass(),"write","","");
            }
            CommandVO vo_get = getCommandVO(id,message,Register.SERVER_COMMAND,Register.PLAYER_COMMAND,ServerVO.class,PlayerVO.class);
            /* 执行指令的部分 */
            if(vo_get instanceof ServerVO){
                ServerVO vo = (ServerVO)vo_get;
                //GameServer sender = vo.getServer();
                String[] args = vo.getArgs();
                String commandName = vo.getCommandName();
                Console.getConsole().runConsoleCommand(commandName,args);
                //Server.getSender().getServer().getGameServer().updateServer(sender);
            }
            if(vo_get instanceof PlayerVO){
                PlayerVO vo = (PlayerVO)vo_get;
                Player player = vo.getPlayer();
                String commandName = vo.getCommandName();
                String[] args = vo.getArgs();
                Server.getSender().getServer().getPluginManager().executeCommand(commandName,args,player);
            }
            socket.getOutputStream().write(0xFF&1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取PacketManager的单例对象
     * @return PacketManager的单利对象
     */

    public static PacketManager getManager() {
        return manager;
    }
}
