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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static cn.jsmod2.core.FileSystem.*;

/**
 * jsmod2 server class
 *
 * @author magiclu550 #(code)jsmod2
 *
 */

public abstract class Server implements Closeable, Reloadable {


    //@PacketCMD private static final int EXECUTE_COMMAND = 0xff;

    private static final int MAX_LENGTH = 8*30;

    protected static final String STOP = "end";

    private long startTime;

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

    protected DatagramSocket udpSocket;

    private Socket socket;

    private ServerSocket serverSocket;

    protected static ConsoleReader lineReader;

    protected Scheduler scheduler;

    protected Lock lock;

    protected static RuntimeServer sender;

    protected GameServer gameServer;

    protected List<Manager> packetManagers;

    protected List<RegisterTemplate> registers;

    protected OpsFile opsFile;


    private boolean isDebug;

    private boolean useUDP;

    protected Properties appProps;



    public Server(GameServer gServer,boolean useUDP) {

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

        this.isDebug = Boolean.parseBoolean(serverProp.getProperty(DEBUG));
        /*
         * 加载插件
         */
        this.plugins = PluginClassLoader.getClassLoader().loadPlugins(pluginDir);

        registerNativeEvents();

        this.useUDP = useUDP;

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
        if(useUDP) {
            this.pool.execute(new ListenerThread());
        }else{
            this.pool.execute(new ListenerThreadTCP());
        }
        this.pool.execute(new GithubConnectThread());
        //this.pool.execute(new ServerThread());
        this.serverLogInfo("the listener thread is starting!!!!");
        startTime =  new Date().getTime();
    }

    public Runtime getRuntimeInfo() {
        return runtimeInfo;
    }

    public Lock getLock() {
        return lock;
    }

    //TODO address和port通过数据包获取
    public void sendPacket(final DataPacket packet){
        sendPacket(packet,serverProp.getProperty(FileSystem.SMOD2_IP),Integer.parseInt(serverProp.getProperty(PLUGIN_PORT)));
    }

    public void sendPacket(final DataPacket packet,String ip,int port){
        Utils.TryCatch(()->{
            byte[] encode = packet.encode();
            //发送端口为插件的端口,ip写死为jsmod2的

            sendData(encode,ip,port);
        });
        ServerPacketEvent event = new ServerPacketEvent(packet);
        pluginManager.callEvent(event);
    }

    public void sendData(byte[] encode,String ip,int port) throws IOException{
        if(useUDP) {
            DatagramPacket pack = new DatagramPacket(encode, encode.length, InetAddress.getByName(ip), port);
            udpSocket.send(pack);
        }else{
            if(socket==null) {
                socket = new Socket();
                socket.connect(new InetSocketAddress(ip,port));
            }
            socket.getOutputStream().write(encode);
        }
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
    private int count = 0;
    private class ListenerThread implements Runnable{
        @Override
        public void run() {
            Utils.TryCatch(()->{
                //注意，一个jsmod2目前只支持一个smod2连接，不支持多个连接
                //在未来版本可能会加入支持多个smod2连接一个服务器
                udpSocket = getSocket(Integer.parseInt(serverProp.getProperty(FileSystem.THIS_PORT)));
                while (true) {
                    DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);
                    udpSocket.receive(request);
                    //manageMessage(request);
                    scheduler.executeRunnable(new PacketHandlerThread(request));

                    count++;
                    if(isDebug){

                        log.debug("one/s:"+getTPS());

                        log.debug(new String(Base64.getDecoder().decode(new String(request.getData(),0,request.getLength()))),count+"::id-message");
                    }
                }
            });
        }
    }

    private class ListenerThreadTCP implements Runnable{
        @Override
        public void run() {
            Utils.TryCatch(()->{
                if(serverSocket == null){
                    serverSocket = new ServerSocket(Integer.parseInt(serverProp.getProperty(THIS_PORT)));
                }

                while (true) {
                    Socket socket = serverSocket.accept();
                    scheduler.executeRunnable(new SocketHandlerThread(socket));
                    if (isDebug) {
                        count++;
                        log.debug("one/s:" + getTPS());
                    }
                }
            });

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public double getTPS(){
        return (double)count/((double)(new Date().getTime()-startTime)/1000.0);
    }



    private void manageMessage(DatagramPacket packet) throws Exception{
        manageMessage(packet.getData(),packet.getLength());
    }

    private void manageMessage(byte[] data,int length) throws Exception{
        String message = new String(data,0,length);
        String[] alls = message.split(";");
        for(String all:alls) {
            //TODO 在这里根据编号分包
            int id = Utils.getResponsePacketId(all);

            packetCommandManage(id, all);

            if (isDebug) {
                log.debug("TPS:"+getTPS()+"[]Thread--get(FACT):" + all,count+"MESSAGE",":"+new String(Base64.getDecoder().decode(all))+"\n");

            }

            for (Manager manager : packetManagers) {
                synchronized (this) {
                    manager.manageMethod(all, id);
                }
            }
            count++;
        }
    }


    public class PacketHandlerThread implements Runnable{

        private DatagramPacket packet;

        public PacketHandlerThread(DatagramPacket packet) {
            this.packet = packet;
        }

        @Override
        public void run() {
            try {
                //接收数据包

                manageMessage(packet);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class SocketHandlerThread implements Runnable{

        private Socket socket;

        public SocketHandlerThread(Socket socket) {
            this.socket = socket;
        }
        //写入请求直接用,隔开
        @Override
        public void run() {

            try{
                byte[] gets = new byte[MAX_LENGTH];
                while (true) {
                    int i = socket.getInputStream().read(gets);
                    if(i == -1){
                        break;
                    }
                    int len = 0;
                    for (byte get : gets) {
                        if (get != 0) {
                            len++;
                        }
                    }
                    String message = new String(gets,0,len);
                    StringBuilder builder = new StringBuilder(message);
                    if(!message.endsWith(";")){
                        int b = 0;
                        while (b!=';'){
                            b = socket.getInputStream().read();
                            builder.append((char) b);
                        }
                    }
                    byte[] after = builder.toString().getBytes();
                    len = 0;
                    for (byte get : after) {
                        if (get != 0) {
                            len++;
                        }
                    }
                    manageMessage(after, len);
                    gets = new byte[MAX_LENGTH];
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
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
