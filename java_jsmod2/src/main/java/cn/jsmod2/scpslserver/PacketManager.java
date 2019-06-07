
/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.scpslserver;

import cn.jsmod2.scpslserver.ex.EventException;
import cn.jsmod2.scpslserver.network.command.PlayerCommandPacket;
import cn.jsmod2.scpslserver.network.command.ServerCommandPacket;
import cn.jsmod2.scpslserver.utils.entity.Player;
import cn.jsmod2.scpslserver.event.Event;
import cn.jsmod2.scpslserver.network.EventBinaryStream;
import cn.jsmod2.scpslserver.network.command.PlayerVO;
import cn.jsmod2.scpslserver.network.command.ServerVO;

import java.util.Properties;


public class PacketManager {

    private static PacketManager manager;

    static {
        manager = new PacketManager();
        Register.getInstance().registerEvents();
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
                Server.getSender().getServer().getPluginManager().executeCommand(commandName,args,sender);
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
