/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.annotations.PacketCMD;
import cn.jsmod2.api.server.Smod2Server;
import cn.jsmod2.event.NativeJoinListener;
import cn.jsmod2.event.packet.ServerPacketEvent;
import cn.jsmod2.log.ILogger;
import cn.jsmod2.log.ServerLogger;
import cn.jsmod2.network.ServerInitPacket;
import cn.jsmod2.plugin.Plugin;
import cn.jsmod2.plugin.PluginClassLoader;
import cn.jsmod2.command.NativeCommand;
import cn.jsmod2.plugin.PluginManager;
import cn.jsmod2.utils.LogFormat;
import cn.jsmod2.utils.Utils;
import jline.console.ConsoleReader;
import cn.jsmod2.network.DataPacket;
import cn.jsmod2.schedule.Scheduler;
import org.fusesource.jansi.Ansi;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static cn.jsmod2.FileSystem.PLUGIN_DIR;
import static cn.jsmod2.FileSystem.getFileSystem;

/**
 * jsmod2 server class
 *
 * @author magiclu550 #(code)jsmod2
 *
 */

public class Server implements Closeable,Reloadable{

    @PacketCMD private static final int INIT_COMMAND = 0x00;

    @PacketCMD private static final int CLOSE_COMMAND = 0x02;

    //@PacketCMD private static final int EXECUTE_COMMAND = 0xff;

    private static final int MAX_LENGTH = 0xffff;

    private static final String STOP = "end";


    // 开辟线程
    // 监听线程 和 一个输入线程
    // 输入线程负责输入命令等

    private static final String PROP = "prop:";

    private static Scanner scanner = new Scanner(System.in);

    private ILogger log;

    private Properties lang;

    private ExecutorService pool = Executors.newFixedThreadPool(5);
    /**用于将服务器对象传递给插件对象*/
    private Server server;


    private Map<String,String> commandInfo;

    private List<Plugin> plugins;

    public final File serverfolder;

    public final Properties serverProp;

    public File pluginDir;

    private PluginManager pluginManager;

    private volatile DatagramSocket socket;

    private Smod2Server smod2Server;

    private static ConsoleReader lineReader;

    private Scheduler scheduler;

    private OpsFile opsFile;

    private Lock lock;

    private static RuntimeServer sender;

    Server(Properties lang) {

        this.lock = new ReentrantLock();

        this.log = ServerLogger.getLogger();

        this.lang = lang;

        this.server = this;

        sender = new RuntimeServer(server);

        this.scheduler = new Scheduler();

        this.serverfolder = new File(System.getProperty("user.dir")).getParentFile();

        //创建plugin文件夹
        this.pluginDir = getFileSystem().pluginDir(server);

        this.serverProp = getFileSystem().serverProperties(server);

        this.pluginManager = new PluginManager(server);

        this.commandInfo = new HashMap<>();

        this.registerNativeInfo();

        this.opsFile = OpsFile.getOpsFile(server);

        this.smod2Server = new Smod2Server();

        /**
         * 加载插件
         */
        this.plugins = PluginClassLoader.getClassLoader().loadPlugins(pluginDir);

        this.pluginManager.registerEvents(new NativeJoinListener(),null);

        start();
    }

    public void start(){
        this.pool.execute(new ListenerThread());
        //this.pool.execute(new ServerThread());
        this.log.info("the listener thread is starting!!!!");
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

    public Smod2Server getSmod2Server(){
        return smod2Server;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public File getServerFolder(){
        return serverfolder;
    }
    public static RuntimeServer getSender(){
        return sender;
    }

    public OpsFile getOpsFile() {
        return opsFile;
    }

    public void help(){
        log.info(LogFormat.textFormat("+================HELP========================+", Ansi.Color.GREEN).toString());
        Set<Map.Entry<String,String>> cmdSet = commandInfo.entrySet();
        for(Map.Entry<String,String> entry:cmdSet){
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.startsWith(PROP)){
                StringBuilder builder = new StringBuilder(value);
                value = builder.substring(PROP.length());
                value = lang.getProperty(value);
            }
            log.info(LogFormat.textFormat(key+": "+value, Ansi.Color.GREEN).toString());
        }
    }


    public Map<String, String> getCommandInfo(){
        return commandInfo;
    }

    //监听Smod2转发端接口
    private DatagramSocket getSocket(int port) throws SocketException {
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
            Jsmod2.startMessage(cn.jsmod2.FileSystem.getFileSystem().langProperties(log));
            pluginManager.clear();
            pluginManager.getPluginClassLoader().loadPlugins(new File(PLUGIN_DIR));
        });
    }
    private void registerNativeInfo(){
        /*
         * prop:指向当前的lang文件
         */
        Set<Map.Entry<String, NativeCommand>> command = Register.getInstance().getNativeCommandMap().entrySet();
        for(Map.Entry<String,NativeCommand> entry:command){
            commandInfo.put(entry.getKey(),entry.getValue().getDescription());
            pluginManager.getCommands().add(entry.getValue());
        }
    }

    public void close(){
        closeAll();
        System.exit(0);
    }

    public void closeAll(){
        disable();
        closeStream();
        log.info(lang.getProperty(STOP+".finish"));
    }
    private void disable(){
        for(Plugin plugin:plugins){
            log.info("unload the plugin named "+plugin.getPluginName());
            plugin.onDisable();
        }
    }


    static Scanner getScanner(){
        return scanner;
    }
    //new line reader
    static ConsoleReader getLineReader() throws IOException{
        if(lineReader == null) {
            lineReader = new ConsoleReader();
        }
        return lineReader;
    }

    private void closeStream(){
        Utils.TryCatch(()->{
            List<InputStream> oStreams = cn.jsmod2.FileSystem.getFileSystem().getInputStreams();
            for(InputStream stream : oStreams){
                stream.close();
            }
            List<OutputStream> iStreams = cn.jsmod2.FileSystem.getFileSystem().getOutputStreams();
            for(OutputStream stream : iStreams){
                stream.close();
            }
            List<BufferedReader> readers = cn.jsmod2.FileSystem.getFileSystem().getReaders();
            for(BufferedReader reader:readers){
                reader.close();
            }
            List<PrintWriter> writers = cn.jsmod2.FileSystem.getFileSystem().getWriters();
            for(PrintWriter writer:writers){
                writer.close();
            }
        });
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

                    if(id == CLOSE_COMMAND){
                        close();
                    }

                    //初始化服务器，第一个数据包

                    if(id == INIT_COMMAND){
                        setServer(message);
                    }

                    Class<?> packet = Register.getInstance().getGetPackets().get(id);
                    if(((Register.SECOND_START_EVENT<=id&&id<Register.MAX_EVENT_ID)||id==Register.FRIST_EVENT)&&packet == null){
                        log.error("no such type packet");
                        continue;
                    }
                    //接收包
                    PacketManager.getManager().manageMethod(message,id);
                }
            });
        }
    }

    /** @deprecated  */
    @Deprecated
    private class ServerThread implements Runnable{
        @Override
        public void run() {
            while(true){
                Utils.TryCatch(()->{
                    DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);
                    DatagramSocket socket = getSocket(Integer.parseInt(serverProp.getProperty("server.init.port")));
                    socket.receive(request);
                    setServer(new String(request.getData(), 0 , request.getLength()));
                });
            }
        }
    }

    private void setServer(String message) throws Exception{
        ServerInitPacket packet = new ServerInitPacket();
        ServerPacketEvent event = new ServerPacketEvent(packet);
        pluginManager.callEvent(event);
        smod2Server.updateServer(packet.decode(message.getBytes(serverProp.getProperty("encode"))));
    }
}
