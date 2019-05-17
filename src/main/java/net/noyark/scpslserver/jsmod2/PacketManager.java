package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.ex.EventException;
import net.noyark.scpslserver.jsmod2.network.EventBinaryStream;

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
        }catch (Exception e){

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
