package cn.jsmod2.core;



import cn.jsmod2.core.protocol.command.*;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.core.ex.EventException;
import cn.jsmod2.core.protocol.EventBinaryStream;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 定义了一个包管理对象，用于管理数据包的接收
 *
 * @author magiclu550
 */

public abstract class Manager implements Cloneable{

    Map<Integer, Class<? extends Event>> events;

    public Manager(){
        events = new HashMap<>();
        for(RegisterTemplate template:Server.getSender().getServer().getRegisters()){
            events.putAll(template.getEvents());
        }
    }

    public abstract void manageMethod(String message,int id);

    /**
     * 通过数据包调用event
     * @param id
     * @param bytes
     */

    public void callEventByPacket(int id, byte[] bytes){


        EventBinaryStream stream = new EventBinaryStream();

        Class<? extends Event> eventClass = events.get(id);
        if(eventClass != null){
            Event event = stream.encode(eventClass,bytes);
            //ServerLogger.getLogger().multiDebug(getClass(),"EVENT_API_KEY:"+event.getApiId(),"\n","");
            Server.getSender().getServer().getPluginManager().callEvent(event);
        }else{
            throw new EventException("No such type of events");
        }
    }

    /**
     * 创建AbstractServerVO和AbstractPlayerVO的子类，将泛型填补
     * 然后加入到这里即可，可以实现指令包的接收
     * @param id 从数据包获取的id
     * @param message 从数据包获取解密后的字符串
     * @param serverCommand 规定的服务器指令id
     * @param playerCommand 规定的玩家指令id
     * @param voServerClass AbstractServerVO的确认泛型的子类
     * @param voPlayerClass AbstractPlayerVO的确认泛型的子类
     * @param <S> AbstractServerVO的确认泛型的子类
     * @param <P> AbstractPlayerVO的确认泛型的子类
     * @return CommandVO的子类
     * @throws UnsupportedEncodingException
     */

    public <S extends AbstractServerVO,P extends AbstractPlayerVO> CommandVO getCommandVO(int id, String message, int serverCommand, int playerCommand, Class<S> voServerClass,Class<P> voPlayerClass) throws UnsupportedEncodingException {
        Properties properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
        byte[] bytes = message.getBytes(properties.getProperty("encode"));//通过utf-8形式获取byte字节数组
        if(id == serverCommand){
            ServerCommandPacket<S> serverCommandPacket = new ServerCommandPacket<>(voServerClass);
            return serverCommandPacket.decode(bytes);
        }
        if(id == playerCommand) {
            PlayerCommandPacket<P> playerCommandPacket = new PlayerCommandPacket<>(voPlayerClass);
            return playerCommandPacket.decode(bytes);
        }

        return null;
    }

    @Override
    public Manager clone() throws CloneNotSupportedException {
        return (Manager) (super.clone());
    }
}
