/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;


import cn.jsmod2.core.annotations.RegisterMethod;
import cn.jsmod2.core.command.OpsFile;
import cn.jsmod2.core.event.packet.ServerPacketEvent;
import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.protocol.Requester;
import cn.jsmod2.core.script.EnvPage;
import cn.jsmod2.core.protocol.SetPacket;
import cn.jsmod2.core.plugin.Plugin;
import cn.jsmod2.core.plugin.PluginClassLoader;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.plugin.PluginManager;
import cn.jsmod2.core.utils.LogFormat;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.core.protocol.DataPacket;
import cn.jsmod2.core.schedule.Scheduler;
import org.fusesource.jansi.Ansi;
import jline.console.ConsoleReader;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static cn.jsmod2.core.FileSystem.PLUGIN_DIR;
import static cn.jsmod2.core.FileSystem.getFileSystem;

/**
 * jsmod2 server class
 *
 * @author magiclu550 #(code)jsmod2
 *
 */

public abstract class Server implements Closeable, Reloadable {



    //@PacketCMD private static final int EXECUTE_COMMAND = 0xff;

    private static final int MAX_LENGTH = 0xffff;

    protected static final String STOP = "end";



    protected Runtime runtimeInfo = Runtime.getRuntime();

    // 开辟线程
    // 监听线程 和 一个输入线程
    // 输入线程负责输入命令等

    protected static final String PROP = "prop:";

    protected static Scanner scanner = new Scanner(System.in);

    protected ILogger log;

    protected Properties lang;

    protected ExecutorService pool = Executors.newFixedThreadPool(5);
    /**用于将服务器对象传递给插件对象*/
    protected Server server;


    protected Map<String,String> commandInfo;

    protected List<Plugin> plugins;

    public final File serverfolder;

    public final Properties serverProp;

    public final File pluginDir;

    protected PluginManager pluginManager;

    protected volatile DatagramSocket socket;


    protected static ConsoleReader lineReader;

    protected Scheduler scheduler;

    protected Lock lock;

    protected static RuntimeServer sender;

    protected GameServer gameServer;

    protected List<Manager> packetManagers;

    protected List<RegisterTemplate> registers;

    protected OpsFile opsFile;



    protected Properties appProps;


    public Server(GameServer gServer) {

        this.lock = new ReentrantLock();

        this.log = ServerLogger.getLogger();

        this.server = this;

        sender = new RuntimeServer(server);

        this.registers = new ArrayList<>();

        this.scheduler = new Scheduler();

        this.serverfolder = new File(System.getProperty("user.dir")).getParentFile();

        registerTemplates(registers,this);

        registerAll();

        this.opsFile = OpsFile.getOpsFile(this);

        this.gameServer = gServer;

        //创建plugin文件夹
        this.pluginDir = getFileSystem().pluginDir(server);

        this.serverProp = getFileSystem().serverProperties(server);

        this.pluginManager = new PluginManager(server);

        this.commandInfo = new HashMap<>();

        this.registerNativeInfo();

        this.packetManagers = new ArrayList<>();

        FileSystem.getFileSystem().readScripts(this);

        registerPacketManger(packetManagers);

        EnvPage.loadConf(serverfolder.toString(),serverfolder+"/emerald");


        /*
         * 加载插件
         */
        this.plugins = PluginClassLoader.getClassLoader().loadPlugins(pluginDir);

        registerNativeEvents();

    }

    public void registerAll(){
        Utils.TryCatch(()->{
            for(RegisterTemplate register:registers){
                Method[] methods = register.getClass().getDeclaredMethods();
                for(Method method:methods){
                    if(method.getAnnotation(RegisterMethod.class)!=null)
                        method.invoke(register);
                }
            }
        });
    }



    public void start(){
        this.pool.execute(new ListenerThread());
        this.pool.execute(new GithubConnectThread());
        //this.pool.execute(new ServerThread());
        this.serverLogInfo("the listener thread is starting!!!!");
    }

    public Runtime getRuntimeInfo() {
        return runtimeInfo;
    }

    public Lock getLock() {
        return lock;
    }

    //TODO address和port通过数据包获取
    public void sendPacket(final DataPacket packet){
        Utils.TryCatch(()->{
            byte[] encode = packet.encode();
            //发送端口为插件的端口,ip写死为jsmod2的
            DatagramPacket pack = new DatagramPacket(encode,encode.length,InetAddress.getByName(serverProp.getProperty(FileSystem.SMOD2_IP)),Integer.parseInt(serverProp.getProperty("data.network.plugin.port")));
            socket.send(pack);
        });
        ServerPacketEvent event = new ServerPacketEvent(packet);
        pluginManager.callEvent(event);
    }

    public ILogger getLogger() {
        return log;
    }


    public Properties getLang() {
        return lang;
    }


    public Server getServer() {
        return server;
    }



    public Scheduler getScheduler() {
        return scheduler;
    }


    public File getServerFolder(){
        return serverfolder;
    }

    public static RuntimeServer getSender(){
        return sender;
    }



    public void help(){
        log.info(LogFormat.textFormat("+================HELP========================+", Ansi.Color.GREEN).toString(),LogFormat.textFormat("[HELP]", Ansi.Color.BLUE).toString());
        Set<Map.Entry<String,String>> cmdSet = commandInfo.entrySet();
        for(Map.Entry<String,String> entry:cmdSet){
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.startsWith(PROP)){
                StringBuilder builder = new StringBuilder(value);
                value = builder.substring(PROP.length());
                value = lang.getProperty(value);
            }
            log.info(LogFormat.textFormat(key+": "+value, Ansi.Color.GREEN).toString(),LogFormat.textFormat("[HELP]", Ansi.Color.BLUE).toString());
        }
    }


    public Map<String, String> getCommandInfo(){
        return commandInfo;
    }

    //监听Smod2转发端接口
    protected DatagramSocket getSocket(int port) throws SocketException {
        return new DatagramSocket(port);
    }

    /**
     * plugin manager
     */
    public PluginManager getPluginManager(){
        return pluginManager;
    }

    public void reload(){
        Utils.TryCatch(()->{
            log.debug("reloading...");
            pluginManager.clear();
            pluginManager.getPluginClassLoader().loadPlugins(new File(PLUGIN_DIR));
        });
    }
    private void registerNativeInfo(){
        /*
         * prop:指向当前的lang文件
         */
        for(RegisterTemplate registerTemplate:registers) {
            Set<Map.Entry<String, NativeCommand>> command = registerTemplate.getNativeCommandMap().entrySet();
            for (Map.Entry<String, NativeCommand> entry : command) {
                commandInfo.put(entry.getKey(), entry.getValue().getDescription());
                pluginManager.getCommands().add(entry.getValue());
            }
        }
    }

    public void close(){
        closeAll();
        System.exit(0);
    }

    public void closeAll(){
        disable();
        closeStream();
        log.info(lang.getProperty(STOP+".finish"),LogFormat.textFormat("[STOP::"+FileSystem.getFileSystem().serverProperties(server).getProperty("smod2.ip")+"]", Ansi.Color.GREEN).toString());
    }
    private void disable(){
        for(Plugin plugin:plugins){
            serverLogInfo("unload the plugin named "+plugin.getPluginName());
            plugin.onDisable();
        }
    }

    public void serverLogInfo(String message){
        Properties properties = FileSystem.getFileSystem().serverProperties(server);
        log.info(message,LogFormat.textFormat("[START::"+properties.getProperty(FileSystem.THIS_IP,"127.0.0.1")+":"+properties.getProperty(FileSystem.THIS_PORT)+"->"+(properties.getProperty(FileSystem.SMOD2_IP).equals(properties.getProperty(FileSystem.THIS_IP))?"":properties.getProperty(FileSystem.SMOD2_IP)+":")+properties.getProperty(FileSystem.PLUGIN_PORT)+"]", Ansi.Color.GREEN).toString());
    }



    public static Scanner getScanner(){
        return scanner;
    }
    //new line reader
    public static ConsoleReader getLineReader() throws IOException{
        if(lineReader == null) {
            lineReader = new ConsoleReader();
        }
        return lineReader;
    }

    private void closeStream(){
        Utils.TryCatch(()->{
            List<InputStream> oStreams = FileSystem.getFileSystem().getInputStreams();
            for(InputStream stream : oStreams){
                stream.close();
            }
            List<OutputStream> iStreams = FileSystem.getFileSystem().getOutputStreams();
            for(OutputStream stream : iStreams){
                stream.close();
            }
            List<BufferedReader> readers = FileSystem.getFileSystem().getReaders();
            for(BufferedReader reader:readers){
                reader.close();
            }
            List<PrintWriter> writers = FileSystem.getFileSystem().getWriters();
            for(PrintWriter writer:writers){
                writer.close();
            }
        });
    }

    private class GithubConnectThread implements Runnable{
        @Override
        public void run() {
            Properties info = FileSystem.getFileSystem().infoProperties();
            log.info(MessageFormat.format("last-format-sha:{0},last-format-info:{1}",info.getProperty("last-commit-sha"),info.getProperty("last-update")),"\n");
            log.info(MessageFormat.format("version:{0}",info.getProperty("version")));
            log.info(MessageFormat.format("thanks for authors:{0}",info.getProperty("authors")));
            log.info(MessageFormat.format("stars:{0},fork:{1}",info.getProperty("stars"),info.getProperty("forks")));
            Utils.getMessageSender().info(">");
        }
    }



    /**
     * 服务器监听线程启动
     * 目前一个java服务器支持一个smod2服
     * 如果要重启smod2服务器 建议先重启java服务器
     * 开启顺序->先开启java服务器->开启smod2
     */
    private class ListenerThread implements Runnable{
        @Override
        public void run() {
            Utils.TryCatch(()->{
                Map<Integer, Class<? extends DataPacket>> packets = new HashMap<>();
                for(RegisterTemplate template:registers){
                    packets.putAll(template.getGetPackets());
                }
                //注意，一个jsmod2目前只支持一个smod2连接，不支持多个连接
                //在未来版本可能会加入支持多个smod2连接一个服务器
                socket = getSocket(Integer.parseInt(serverProp.getProperty(FileSystem.THIS_PORT)));

                while (true) {

                    //接收数据包

                    DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);

                    socket.receive(request);

                    String message = new String(request.getData(), 0 , request.getLength());



                    //TODO 在这里根据编号分包
                    int id = Utils.getResponsePacketId(message);

                    packetCommandManage(id,message);


                    for(Manager manager:packetManagers){
                        manager.manageMethod(message,id);
                    }
                }
            });
        }
    }




    /** 包指令的处理 */
    public abstract void packetCommandManage(int id,String message) throws Exception;
    /** 注册数据包管理员 */
    public abstract void registerPacketManger(List<Manager> managers);
    /** 通过RegisterTemplates注册服务器信息 */
    public abstract void registerTemplates(List<RegisterTemplate> registers,Server server);

    /** 注册原生的事件 */
    public abstract void registerNativeEvents();


    /** 获取GameServer */
    public GameServer getGameServer(){
        return gameServer;
    }

    /**采用多对象制度，分发一个请求，创建一个request对象*/
    public Requester getRequester(SetPacket packet) {
        return new Requester(this,packet);
    }

    public List<RegisterTemplate> getRegisters() {
        return registers;
    }

    public void setLang(Properties lang) {
        this.lang = lang;
    }

    public OpsFile getOpsFile() {
        return opsFile;
    }
}
