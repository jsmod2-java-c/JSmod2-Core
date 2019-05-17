package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.command.*;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.network.AdminQueryPacket;
import net.noyark.scpslserver.jsmod2.network.DataPacket;
import net.noyark.scpslserver.jsmod2.network.ServerInitPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.noyark.scpslserver.jsmod2.Jsmod2.START;

/**
 * 所有注册的中心类
 * @author magiclu550
 */

public class Register {

    /**
     * 注册语言时，首先按照标准格式添加语言
     * 参考resources格式
     */

    public void registerLang(){
        registerLang.add("zh");
        registerLang.add("en");
        registerLang.add("ru");
        registerLang.add("jp");
        registerLang.add("ar");
        registerLang.add("sp");
    }


    public void registerNativeCommand(){
        nativeCommandMap.put("stop",new StopCommand());
        nativeCommandMap.put("help",new HelpCommand());
        nativeCommandMap.put("plugins",new PluginsCommand());
        nativeCommandMap.put("reboot",new RebootCommand());
    }

    public void registerStartInfo(){
        startInfo.add(START+".info");
        startInfo.add(START+".thanks");
        startInfo.add(START+".warn");
        startInfo.add(START+".connect");
    }

    public void registerPacket(){
        packets.put(0x00, ServerInitPacket.class);
        packets.put(0x01, AdminQueryPacket.class);
        packets.put(0x02,null);//ClosePacket no response
    }

    public void registerSuccessInfo(){
        successInfo.add("start.finish");
    }

    public void registerServerProperties(){
        serverProperties.put("this.port","19935");//java端ip
        serverProperties.put("data.network.plugin.port","19938");//插件端ip
        serverProperties.put("decode","utf-8");//解码字符集
        serverProperties.put("encode","utf-8");//编码字符集
    }

    /**
     * 格式:
     * put(事件id,事件名.class)
     * 事件id对应数据包id
     */
    public void registerEvents(){
    }

    public List<String> getRegisterLang(){
        return registerLang;
    }

    public Map<String, NativeCommand> getNativeCommandMap(){
        return nativeCommandMap;
    }

    public List<String> getStartInfo() {
        return startInfo;
    }


    public List<String> getSuccessInfo() {
        return successInfo;
    }

    public Map<Integer,Class<? extends DataPacket>> getPackets(){
        return packets;
    }

    public static Register getInstance(){
        return register;
    }


    private static Register register;

    private static List<String> registerLang = new ArrayList<>();

    private Map<String, NativeCommand> nativeCommandMap = new HashMap<>();

    private List<String> startInfo = new ArrayList<>();

    private List<String> successInfo = new ArrayList<>();

    private Map<Integer,Class<? extends DataPacket>> packets = new HashMap<>();

    private Map<String,String> serverProperties = new HashMap<>();

    private Map<Integer, Class<? extends Event>> events = new HashMap<>();

    public Map<String, String> getServerProperties() {
        return serverProperties;
    }

    public Map<Integer, Class<? extends Event>> getEvents() {
        return events;
    }

    public void setEvents(Map<Integer,Class<? extends Event>> events) {
        this.events = events;
    }

    static {
        register = new Register();
    }
}
