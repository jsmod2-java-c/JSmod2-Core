package net.noyark.scpslserver.jsmod2;

import net.noyark.Smod2Server;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.ex.EventException;
import net.noyark.scpslserver.jsmod2.network.EventBinaryStream;
import net.noyark.scpslserver.jsmod2.network.command.PlayerCommandPacket;
import net.noyark.scpslserver.jsmod2.network.command.PlayerVO;
import net.noyark.scpslserver.jsmod2.network.command.ServerCommandPacket;
import net.noyark.scpslserver.jsmod2.network.command.ServerVO;

import java.util.Properties;

import static net.noyark.scpslserver.jsmod2.Register.getInstance;


public class PacketManager {

    private static PacketManager manager;

    static {
        manager = new PacketManager();
        getInstance().registerEvents();
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
            if(id == 1||(0x03<=id&&id<=0x52)){
                callEventByPacket(id,bytes);
            }
            /* 执行指令的部分 */
            if(id == 0x55){
                ServerCommandPacket serverCommandPacket = new ServerCommandPacket();
                ServerVO vo = serverCommandPacket.decode(bytes);
                Smod2Server sender = vo.getServer();
                String[] args = vo.getArgs();
                String commandName = vo.getCommandName();
                Server.getSender().getServer().getPluginManager().excuteCommand(commandName,args,sender);
            }
            if(id == 0x56){
                PlayerCommandPacket playerCommandPacket = new PlayerCommandPacket();
                PlayerVO vo = playerCommandPacket.decode(bytes);
                Player player = vo.getPlayer();
                String commandName = vo.getCommandName();
                String[] args = vo.getArgs();
                Server.getSender().getServer().getPluginManager().excuteCommand(commandName,args,player);
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

        Class<? extends Event> eventClass = getInstance().getEvents().get(id);
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
