package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.network.DataPacket;
import net.noyark.scpslserver.jsmod2.utils.Utils;

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
        Utils.TryCatch(()->{
            Properties properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
            byte[] bytes = message.getBytes(properties.getProperty("encode"));//通过utf-8形式获取byte字节数组
        });
    }

    /**
     * 通过数据包调用event
     * @param id
     * @param bytes
     * @param packet
     */

    public void callEventByPacket(int id, byte[] bytes, DataPacket packet){

        Class<? extends Event> eventClass = getInstance().getEvents().get(id);

        Event event = eventClass.cast(packet.decode(bytes));

        Server.getSender().getServer().getPluginManager().callEvent(event);

    }

    public static PacketManager getManager() {
        return manager;
    }
}
