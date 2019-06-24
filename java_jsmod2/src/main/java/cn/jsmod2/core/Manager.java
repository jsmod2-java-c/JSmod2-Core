package cn.jsmod2.core;

import cn.jsmod2.Register;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.core.ex.EventException;
import cn.jsmod2.core.protocol.EventBinaryStream;
import cn.jsmod2.network.command.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public interface Manager {

    void manageMethod(String message,int id);

    /**
     * 通过数据包调用event
     * @param id
     * @param bytes
     */

    default void callEventByPacket(int id, byte[] bytes){

        EventBinaryStream stream = new EventBinaryStream();

        Map<Integer, Class<? extends Event>> events = new HashMap<>();
        for(RegisterTemplate template:Server.getSender().getServer().getRegisters()){
            events.putAll(template.getEvents());
        }
        Class<? extends Event> eventClass = events.get(id);
        if(eventClass != null){
            Event event = stream.encode(eventClass,bytes);
            Server.getSender().getServer().getPluginManager().callEvent(event);
        }else{
            throw new EventException("No such type of events");
        }
    }

    default CommandVO getCommandVO(int id,String message,int serverCommand,int playerCommand) throws UnsupportedEncodingException {
        Properties properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
        byte[] bytes = message.getBytes(properties.getProperty("encode"));//通过utf-8形式获取byte字节数组
        if(id == serverCommand){
            ServerCommandPacket serverCommandPacket = new ServerCommandPacket();
            return serverCommandPacket.decode(bytes);
        }
        if(id == playerCommand) {
            PlayerCommandPacket playerCommandPacket = new PlayerCommandPacket();
            return playerCommandPacket.decode(bytes);
        }
        return null;
    }

}
