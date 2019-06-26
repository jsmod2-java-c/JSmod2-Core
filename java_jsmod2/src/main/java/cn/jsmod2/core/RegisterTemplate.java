package cn.jsmod2.core;


import cn.jsmod2.core.annotations.RegisterMethod;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.core.protocol.DataPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定义了一个注册类的模板，在Server子类注册
 *
 * @author magiclu550
 */
public abstract class RegisterTemplate {

    public List<String> getRegisterLang(){
        return registerLang;
    }

    public Map<String, NativeCommand> getNativeCommandMap(){
        return nativeCommandMap;
    }

    public List<String> getStartInfo() {
        return startInfo;
    }


    public Map<Class<? extends Exception>, String> getEx_methods() {
        return ex_methods;
    }

    public List<String> getSuccessInfo() {
        return successInfo;
    }

    public Map<Class<? extends DataPacket>,Integer> getPackets(){
        return packets;
    }

    public Map<Integer, Class<? extends Event>> getEvents() {
        return events;
    }


    public Map<String, String> getServerProperties() {
        return serverProperties;
    }


    protected List<String> registerLang = new ArrayList<>();

    protected Map<String, NativeCommand> nativeCommandMap = new HashMap<>();

    protected List<String> startInfo = new ArrayList<>();

    protected List<String> successInfo = new ArrayList<>();

    protected Map<Class<? extends DataPacket>,Integer> packets = new HashMap<>();

    protected Map<String,String> serverProperties = new HashMap<>();

    protected Map<Integer, Class<? extends Event>> events = new HashMap<>();

    protected Map<Integer,Class<? extends DataPacket>> getPackets = new HashMap<>();

    protected Map<Class<? extends Exception>,String> ex_methods = new HashMap<>();

    public Map<Integer, Class<? extends DataPacket>> getGetPackets() {
        return getPackets;
    }

    public void setGetPackets(Map<Integer, Class<? extends DataPacket>> getPackets) {
        this.getPackets = getPackets;
    }

    @RegisterMethod
    public void registerLang(){

    }
    @RegisterMethod
    public void registerNativeCommand(){

    }
    @RegisterMethod
    public void registerStartInfo(){

    }
    @RegisterMethod
    public void registerPacket(){

    }
    @RegisterMethod
    public void registerSuccessInfo(){

    }
    @RegisterMethod
    public void registerServerProperties(){
        serverProperties.put(FileSystem.SERVER_DECODE,"utf-8");//解码字符集
        serverProperties.put(FileSystem.SERVER_ENCODE,"utf-8");//编码字符集
        serverProperties.put(FileSystem.PLUGIN_PORT,"19938");//对方端port(或者插件端)
        //serverProperties.put(FileSystem.SERVER_INIT_PORT,"19939");//服务端初始化的端口，传输server信息
        serverProperties.put(FileSystem.THIS_PORT,"19935");//本服务端的port
        serverProperties.put(FileSystem.SMOD2_IP,"127.0.0.1");//对方端的ip
        serverProperties.put(FileSystem.THIS_IP,"127.0.0.1");//本端的ip
        serverProperties.put(FileSystem.EMERALD_COMPILER,"java");//java c++ kotlin选项，c++ native未来将被替代
        serverProperties.put(FileSystem.DEBUG,"false");//debug模式
    }
    @RegisterMethod
    public void registerException(){

    }
    @RegisterMethod
    public void registerEvents(){

    }
}
