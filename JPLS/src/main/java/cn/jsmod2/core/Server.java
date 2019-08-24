/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;


import cn.jsmod2.Register;
import cn.jsmod2.core.annotations.RegisterMethod;
import cn.jsmod2.core.command.OpsFile;
import cn.jsmod2.core.event.packet.ServerPacketEvent;
import cn.jsmod2.core.interapi.IServer;
import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.protocol.*;
import cn.jsmod2.core.script.EmeraldScriptVM;
import cn.jsmod2.core.script.EnvPage;
import cn.jsmod2.core.plugin.Plugin;
import cn.jsmod2.core.plugin.PluginClassLoader;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.plugin.PluginManager;
import cn.jsmod2.core.utils.Future;
import cn.jsmod2.core.utils.LogFormat;
import cn.jsmod2.core.utils.Result;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.core.schedule.Scheduler;
import cn.jsmod2.panel.NettyServer;
import org.fusesource.jansi.Ansi;
import jline.console.ConsoleReader;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static cn.jsmod2.core.FileSystem.*;
import static cn.jsmod2.core.utils.Utils.getFullBytes;
import static cn.jsmod2.core.utils.Utils.getLen;

/**
 * jsmod2 cn.jsmod2.server class
 *
 * @author magiclu550 #(code)jsmod2
 *
 */

public abstract class Server implements IServer {

    public static final String START = "start";

    private static final int MAX_LENGTH = 8*30;

    private static final String STOP = "end";

    private static final String PROP = "prop:";

    private static Scanner scanner = new Scanner(System.in);

    private static ConsoleReader lineReader;

    private static RuntimeServer sender;

    protected ILogger log;

    protected Properties lang;

    protected Server server;

    protected GameServer gameServer;

    protected List<Plugin> plugins;

    protected Properties appProps;

    protected PluginManager pluginManager;

    private long startTime;

    private long startSuccessTime;

    private ExecutorService pool = Executors.newFixedThreadPool(5);

    private Map<String,String> commandInfo;

    private Closeable serverSocket;

    private Scheduler scheduler;

    private Lock lock;

    private List<Manager> packetManagers;

    private List<RegisterTemplate> registers;

    private OpsFile opsFile;

    private boolean isDebug;

    private boolean useUDP;

    public final File serverfolder;

    public final File pluginDir;

    public final Properties serverProp;

    public Server(GameServer gServer,boolean useUDP) {

        Server.sender = new RuntimeServer(this);

        this.startTime =  new Date().getTime();

        this.lock = new ReentrantLock();

        this.log = ServerLogger.getLogger();

        this.server = this;

        this.registers = new ArrayList<>();

        this.scheduler = new Scheduler();

        String file = Server.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        file = file.substring(0,file.lastIndexOf("/")+1);

        this.serverfolder = new File(file);

        this.log.multiInfo(this.getClass(),"Server's folder"+serverfolder,"","");

        this.registerTemplates(registers,this);

        this.registerAll();


        this.opsFile = OpsFile.getOpsFile(this);

        this.gameServer = gServer;

        this.pluginDir = getFileSystem().pluginDir(server);

        this.serverProp = getFileSystem().serverProperties(server);


        getLogger().multiInfo(getClass(),"Connecting the multiAdmin | LocalAdmin","","");



        this.pluginManager = new PluginManager(server);

        this.commandInfo = new HashMap<>();

        this.registerNativeInfo();

        this.packetManagers = new ArrayList<>();

        FileSystem.getFileSystem().readScripts(this);

        registerPacketManger(packetManagers);

        EnvPage.loadConf(serverfolder.toString(),serverfolder+"/emerald");

        this.isDebug = Boolean.parseBoolean(serverProp.getProperty(DEBUG));

        this.registerNativeEvents();

        try {
            this.chooseLangOrStart();

            scheduler.executeRunnable(()->Utils.TryCatch(this::startConsoleCommand));
            if(Boolean.parseBoolean(serverProp.getProperty(Register.START_NETTY_SERVER,"true"))) {
                scheduler.executeRunnable(new NettyServer());
            }
            ServerSocket socket = new ServerSocket(20003);

            socket.accept();

        }catch (Exception e){

        }

        getLogger().multiInfo(getClass(),"Connect successfully,loading plugins...","","");
        //加载插件
        this.plugins = PluginClassLoader.getClassLoader().loadPlugins(pluginDir);

        this.useUDP = useUDP;

    }

    public void start(Class<?> main,String[] args) {
        startWatch(main,args);
    }

    public void startWatch(Class<?> main,String[] args) {
        Utils.TryCatch(()-> {
            this.log.multiInfo(this.getClass(),main.getSimpleName() + "::start::" + main.getName(),"","");
            this.executeEmerald(args, true);
            this.start();
            this.successTime();
        });
    }
    public void start(){
        if(useUDP) {
            this.pool.execute(new ListenerThread());
        }else{
            this.pool.execute(new ListenerThreadTCP());
        }
        String log = logListener("yyyy-MM-dd HH.mm.ss",this::getMax,SMOD2_LOG_FILE);
        if(log != null) {
            this.pool.execute(new LogListener(log, Integer.parseInt(serverProp.getProperty(SMOD2_LOG_INTERVAL,"2000")),"yyyy-MM-dd HH.mm.ss",this::getMax,SMOD2_LOG_FILE));
        }
        String log1 = logListener("yyyy-MM-dd_HH_mm",(format, x1, x2) -> getMultiSCPMax(format,x1,x2,"MA"), Register.CONSOLE_LOG);
        if(log1 != null){
            this.pool.execute(new LogListener(log1,Integer.parseInt(serverProp.getProperty(SMOD2_LOG_INTERVAL,"2000")),"yyyy-MM-dd_HH_mm",(format, x1, x2) -> getMultiSCPMax(format,x1,x2,"MA"),Register.CONSOLE_LOG));
            this.pool.execute(new LogListener(log1,Integer.parseInt(serverProp.getProperty(SMOD2_LOG_INTERVAL,"2000")),"yyyy-MM-dd_HH_mm",(format, x1, x2) -> getMultiSCPMax(format,x1,x2,"SCP"),Register.CONSOLE_LOG));
        }



        if(Boolean.parseBoolean(serverProp.getProperty(GITHUB))) {
            this.pool.execute(new GithubConnectThread());
        }
        //this.pool.execute(new ServerThread());

        //this.pool.execute(new Smod2LogThread());
        this.startSuccessTime = new Date().getTime();
    }

    private int getMax(SimpleDateFormat format,File x1,File x2){
        try {
            String name1 = x1.getName().substring("Round".length()).trim();
            String name2 = x2.getName().substring("Round".length()).trim();
            Date date1 = format.parse(name1);
            Date date2 = format.parse(name2);

            return (int)(date2.getTime()-date1.getTime());
        }catch (Exception e){
            Utils.printException(e);
        }
        return 0;
    }

    private int getMultiSCPMax(SimpleDateFormat format,File x1,File x2,String str){
        try{
            String name1 = x1.getName().replace("_"+str+"_output_log.txt","");
            String name2 = x2.getName().replace("_"+str+"_output_log.txt","");
            Date date1 = format.parse(name1);
            Date date2 = format.parse(name2);

            return (int)(date2.getTime()-date1.getTime());
        }catch (Exception e){
            Utils.printException(e);
        }

        return 0;
    }

    public <T> T sendGetPacket(GetPacket packet,Class<T> type){
        return type.cast(packet.send());
    }

    public void sendSetPacket(SetPacket packet){
        packet.send();
    }

    public File getServerfolder() {
        return serverfolder;
    }

    public Properties getServerProperties() {
        return serverProp;
    }

    public void sendPacket(final DataPacket packet){
        sendPacket(packet,false);
    }

    public Future sendPacketGetResult(final  DataPacket packet){
        return sendPacket(packet,true);
    }

    /** 包指令的处理 */
    public abstract void packetCommandManage(int id,String message) throws Exception;
    /** 注册数据包管理员 */
    public abstract void registerPacketManger(List<Manager> managers);
    /** 通过RegisterTemplates注册服务器信息 */
    public abstract void registerTemplates(List<RegisterTemplate> registers,Server server);
    /** 注册原生的事件 */
    public abstract void registerNativeEvents();


    public void help(){
        log.multiInfo(this.getClass(),LogFormat.textFormat("+================HELP========================+", Ansi.Color.GREEN).toString(),LogFormat.textFormat("[HELP]", Ansi.Color.BLUE).toString(),"");
        Set<Map.Entry<String,String>> cmdSet = commandInfo.entrySet();
        for(Map.Entry<String,String> entry:cmdSet){
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.startsWith(PROP)){
                StringBuilder builder = new StringBuilder(value);
                value = builder.substring(PROP.length());
                value = lang.getProperty(value);
            }
            log.multiInfo(this.getClass(),LogFormat.textFormat(key+": "+value, Ansi.Color.GREEN).toString(),LogFormat.textFormat("[HELP]", Ansi.Color.BLUE).toString(),"");
        }
    }


    public Map<String, String> getCommandInfo(){
        return commandInfo;
    }


    public PluginManager getPluginManager(){
        return pluginManager;
    }

    public void reload(){
        Utils.TryCatch(()->{
            log.multiDebug(getClass(),"reloading...","","");
            pluginManager.clear();
            pluginManager.getPluginClassLoader().loadPlugins(new File(PLUGIN_DIR));
        });
    }


    public void close(){
        closeAll();
        System.exit(0);
    }


    public Future sendData(byte[] encode,String ip,int port,boolean result) {
        Future future = new Result();
        try {
            if (useUDP) {
                DatagramPacket pack = new DatagramPacket(encode, encode.length, InetAddress.getByName(ip), port);
                ((DatagramSocket) serverSocket).send(pack);
            } else {
                Socket socket = new Socket();
                if(!socket.isConnected())
                    socket.connect(new InetSocketAddress(ip, port));
                socket.getOutputStream().write(encode);
                if (result) {
                    byte[] bytes = new byte[MAX_LENGTH];
                    byte[] after = getFullBytes(socket, bytes);
                    future.set(after);
                }
                socket.close();
            }
        }catch (Exception e){
            log.multiError(getClass(),e.getMessage(),"","");
        }
        return future;
    }

    /** 获取GameServer */
    public GameServer getGameServer(){
        return gameServer;
    }

    /**采用多对象制度，分发一个请求，创建一个request对象*/
    public Requester getRequester(ControlPacket packet) {
        return new Requester(this,packet);
    }

    public List<RegisterTemplate> getRegisters() {
        return registers;
    }

    public OpsFile getOpsFile() {
        return opsFile;
    }

    public Closeable getServerSocket() {
        return serverSocket;
    }

    public double getTPS(){
        return (double)count/((double)(new Date().getTime()-startSuccessTime)/1000.0);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getStartSuccessTime() {
        return startSuccessTime;
    }

    public Lock getLock() {
        return lock;
    }

    public String  getPluginDir() {
        return pluginDir.toString();
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

    private void registerAll(){
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

    private void serverLogInfo(String message){
        log.multiInfo(getClass(),message,"","");
    }



    private Future sendPacket(final DataPacket packet,boolean result){
        if(isDebug) {
            log.multiDebug(getClass(), "PACKET_TYPE:" + packet.getClass().getSimpleName(), "", "");
        }
        return sendPacket(packet,serverProp.getProperty(FileSystem.SMOD2_IP),Integer.parseInt(serverProp.getProperty(PLUGIN_PORT)),result);
    }

    private Future sendPacket(final DataPacket packet,String ip,int port,boolean result){
        ServerPacketEvent event = new ServerPacketEvent(packet);
        pluginManager.callEvent(event);

        byte[] encode = packet.encode();

        //发送端口为插件的端口,ip写死为jsmod2的
        if(encode!=null)
            return sendData(encode, ip, port,result);
        else
            log.multiError(getClass(),"PROTOCOL: NULL","","");

        return null;
    }



    //监听Smod2转发端接口
    private Closeable getSocket(int port) throws IOException {
        if(useUDP) {
            return new DatagramSocket(port);
        }else{
            return new ServerSocket(port);
        }
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



    private void manageMessage(DatagramPacket packet,Socket socket) throws Exception{
        manageMessage(packet.getData(),packet.getLength(),socket);
    }

    private void manageMessage(byte[] data,int length,Socket socket) throws Exception{
        String message = new String(data,0,length);
        String[] alls = message.split(";");
        for(String all:alls) {

            int id = Utils.getResponsePacketId(all);

            packetCommandManage(id, all);

            if (isDebug) {
                log.debug("TPS:"+getTPS()+"[]Thread--get(FACT):" + all,count+"MESSAGE",":"+new String(Base64.getDecoder().decode(all))+"\n");

            }


            for (Manager manager : packetManagers) {
                synchronized (this) {
                    manager.manageMethod(all, id,socket);
                }
            }
            count++;
        }
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

    private void closeAll(){
        disable();
        closeStream();
        log.multiInfo(this.getClass(),lang.getProperty(STOP+".finish"),LogFormat.textFormat("[STOP::"+FileSystem.getFileSystem().serverProperties(server).getProperty("smod2.ip")+"]", Ansi.Color.GREEN).toString(),"");
    }
    private void disable(){
        if(plugins != null){
            for(Plugin plugin:plugins){
                if(plugin!=null) {
                    serverLogInfo("unload the plugin named " + plugin.getPluginName());
                    plugin.onDisable();
                }
            }
        }
    }
    private void startMessage(Properties langProperties,Server server){
        //plugin dir
        for(RegisterTemplate template:server.getRegisters()) {
            for (String info : template.getStartInfo()) {
                server.serverLogInfo(langProperties.getProperty(info));
            }
        }
    }

    private class GithubConnectThread implements Runnable{
        @Override
        public void run() {
            Properties info = FileSystem.getFileSystem().infoProperties();
            log.multiInfo(this.getClass(),MessageFormat.format("last-commit-sha:{0},last-commit-info:{1}",info.getProperty("last-commit-sha"),info.getProperty("last-update")),"\n","");
            log.multiInfo(this.getClass(),MessageFormat.format("version:{0}",info.getProperty("version")),"","");
            log.multiInfo(this.getClass(),MessageFormat.format("thanks for authors:{0}",info.getProperty("authors")),"","");
            log.multiInfo(this.getClass(),MessageFormat.format("stars:{0},fork:{1}",info.getProperty("stars"),info.getProperty("forks")),"","");
            Utils.getMessageSender().info(">");
        }
    }




    public class PacketHandlerThread implements Runnable{

        private DatagramPacket packet;

        PacketHandlerThread(DatagramPacket packet) {
            this.packet = packet;
        }

        @Override
        public void run() {
            try {
                //接收数据包

                manageMessage(packet,null);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 每连接一个Socket 会开辟一个线程，当Socket一段时间不使用，就会废弃，
     * 此时这个线程的Socket不再接收数据
     */
    public class SocketHandlerThread implements Runnable{

        private Socket socket;

        SocketHandlerThread(Socket socket) {
            this.socket = socket;
        }
        //写入请求直接用,隔开
        @Override
        public void run() {

            try{
                byte[] gets = new byte[MAX_LENGTH];
                socket.getInputStream().read(gets);
                byte[] after = getFullBytes(socket,gets);
                manageMessage(after, getLen(after),socket);
            }catch (Exception e){
                Utils.printException(e);
            }finally {
                try {
                    socket.close();
                }catch (IOException e){
                    Utils.printException(e);
                }
            }
        }
    }

    private Integer count = 0;
    private class ListenerThread implements Runnable{
        @Override
        public void run() {
            Utils.TryCatch(()->{
                //注意，一个jsmod2目前只支持一个smod2连接，不支持多个连接
                //在未来版本可能会加入支持多个smod2连接一个服务器
                serverSocket = getSocket(Integer.parseInt(serverProp.getProperty(FileSystem.THIS_PORT)));
                while (true) {
                    DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);
                    ((DatagramSocket)serverSocket).receive(request);
                    //manageMessage(request);
                    scheduler.executeRunnable(new PacketHandlerThread(request));
                    lock.lock();
                    count++;
                    lock.unlock();
                    if(isDebug){

                        log.multiDebug(this.getClass(),"one/s:"+getTPS(),"","");

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
                    serverSocket = getSocket(Integer.parseInt(serverProp.getProperty(THIS_PORT)));
                }

                while (true) {
                    Socket socket = ((ServerSocket)serverSocket).accept();
                    scheduler.executeRunnable(new SocketHandlerThread(socket));
                    if (isDebug) {
                        lock.lock();
                        count++;
                        lock.unlock();
                        log.multiDebug(getClass(),"one/s:" + getTPS(),"","");
                    }
                }
            });

        }
    }

    /**
     * 监听smod2的log文件
     * 一个线程监听，一个线程放行
     */
    @Deprecated
    private class Smod2LogThread implements Runnable{
        @Override
        public void run() {
            String fileName = serverProp.getProperty(SMOD2_LOG_FILE);
            File file = new File(fileName);
            if(file.exists()) {
                try {
                    BufferedReader reader = Utils.getReader(file);
                    BlockingQueue<String> queue = new SynchronousQueue<>();
                    scheduler.executeRunnable(()->{
                        try {
                            for (; ; ) {
                                String message = reader.readLine();
                                if (message != null) {
                                    queue.put(message);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    scheduler.executeRunnable(()->{
                        for(;;) {
                            try {
                                String take = queue.take();
                                log.info(take);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * something find in https://crunchify.com
     * 以下代码的产生来之不易，感谢
     * JunHe友情提供代码地址
     * 校长提供log文件格式
     * GNX和菠萝协助解决问题
     */
    private static int crunchifyCounter = 0;
    private class LogListener implements Runnable {

        private int crunchifyRunEveryNSeconds;
        private long lastKnownPosition = 0;
        private boolean shouldIRun = true;
        private File crunchifyFile;
        private String timeFormat;

        private Max max;

        private String fileProperty;

        public LogListener(String file,int myInterval,String timeFormat,Max max,String prop) {
            this.crunchifyRunEveryNSeconds = myInterval;
            this.crunchifyFile = new File(file);
            this.timeFormat = timeFormat;
            this.max = max;
            this.fileProperty = prop;
        }

        private void printLine(String message) {
            try{
                log.multiInfo(getClass(),new String(message.getBytes("ISO-8859-1"),System.getProperty("file.encoding")),"","");

            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }

        public void stopRunning() {
            shouldIRun = false;
        }

        public void run() {
            try {
                int count = 0;
                while (shouldIRun) {
                    Thread.sleep(crunchifyRunEveryNSeconds);

                    String log = logListener(timeFormat,max,fileProperty);

                    if(!"same".equals(log)){
                        if(log !=null) {
                            crunchifyFile = new File(log);
                        }

                    }

                    long fileLength = crunchifyFile.length();
                    if (fileLength > lastKnownPosition) {
                        // Reading and writing file
                        RandomAccessFile readWriteFileAccess = new RandomAccessFile(crunchifyFile, "rw");
                        readWriteFileAccess.seek(lastKnownPosition);
                        String crunchifyLine;
                        while ((crunchifyLine = readWriteFileAccess.readLine()) != null) {
                            if(count!=0) {
                                this.printLine(crunchifyLine);
                            }
                            crunchifyCounter++;
                        }
                        lastKnownPosition = readWriteFileAccess.getFilePointer();
                        readWriteFileAccess.close();
                    } else {
                        if (isDebug)
                            this.printLine("Hmm.. Couldn't found new line after line # " + crunchifyCounter);
                    }
                    count++;
                }
            } catch (Exception e) {
                stopRunning();
            }
            if (isDebug)
                this.printLine("Exit the program...");
        }
    }

    //new line reader
    public static ConsoleReader getLineReader() throws IOException{
        if(lineReader == null) {
            lineReader = new ConsoleReader();
            lineReader.addCompleter(Console.getSimpleConsole());
        }
        return lineReader;
    }

    static Scanner getScanner(){
        return scanner;
    }

    public void setLang(Properties lang) {
        this.lang = lang;
    }

    private void executeEmerald(String[] args,boolean exit){
        server.serverLogInfo("this cn.jsmod2.server uses the Emerald "+ Server.getSender().getServer().serverProp.getProperty(EMERALD_COMPILER,"java")+" compiler v0.1 Engine By MagicLu550");
        if(args.length!=0){
            for(String arg:args)
                EmeraldScriptVM.getVM().parse(arg);
            if(exit)
                System.exit(0);
        }
    }

    private void successTime(){
        for(RegisterTemplate template:server.getRegisters()) {
            for (String success : template.getSuccessInfo()) {
                server.serverLogInfo(MessageFormat.format(lang.getProperty(success), (server.getStartSuccessTime()-server.getStartTime()) + ""));
            }
        }
    }

    private void chooseLangOrStart() throws IOException{
        Properties langProperties = FileSystem.getFileSystem().langProperties(log,server);
        server.setLang(langProperties);
        FileSystem.getFileSystem().initLang(langProperties);
        startMessage(langProperties,server);
    }

    private void startConsoleCommand() throws IOException{
        Console.getConsole().commandInput();
    }

    private File[] beforeFile;

    private String logListener(String timeFormat,Max max,String fileProperty){
        String serverProps = serverProp.getProperty(fileProperty);
        File file = new File(serverProps);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        if(file.exists()){
            File[] files = file.listFiles();
            if(files!=null) {
                if(beforeFile == null){
                    beforeFile = files;
                }else{
                    if(Arrays.asList(beforeFile).equals(Arrays.asList(files))){
                        return "same";
                    }
                }
                File lastFile = Arrays.stream(files)
                        .filter(x->x.getName().endsWith(".log")||x.getName().endsWith(".txt"))
                        .sorted((x1,x2)->max.getMax(format,x1,x2)).collect(Collectors.toList()).get(0);
                //Round 2019-07-10 08.47.31
                return lastFile.toString();


            }
        }
        return null;
    }

    interface Max{


        int getMax(SimpleDateFormat format, File x1, File x2);

    }

}
