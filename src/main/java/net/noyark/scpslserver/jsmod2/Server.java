package net.noyark.scpslserver.jsmod2;

import net.noyark.Smod2Server;
import net.noyark.scpslserver.jsmod2.annotations.PacketCMD;
import net.noyark.scpslserver.jsmod2.command.*;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;
import net.noyark.scpslserver.jsmod2.network.DataPacket;
import net.noyark.scpslserver.jsmod2.plugin.PluginClassLoader;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.noyark.scpslserver.jsmod2.FileSystem.PLUGIN_DIR;
import static net.noyark.scpslserver.jsmod2.FileSystem.getFileSystem;

/**
 * jsmod2 server class
 *
 * @author magiclu550 #(code)jsmod2
 *
 */

public class Server {

    @PacketCMD private static final int CLOSE_COMMAND = 0x02;

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

    private static CommandConsoleSender sender;

    private Map<String,String> commandInfo;

    private List<Plugin> plugins;

    public final File serverfolder;

    public final Properties serverProp;

    public File pluginDir;

    private PluginManager pluginManager;

    private volatile DatagramSocket socket;

    private Smod2Server smod2Server;


    Server(ILogger log, Properties lang) {
        Register.getInstance().registerPacket();

        this.log = log;

        this.lang = lang;

        this.server = this;

        this.serverfolder = new File(System.getProperty("user.dir")).getParentFile();

        //创建plugin文件夹
        this.pluginDir = getFileSystem().pluginDir(server);

        this.serverProp = getFileSystem().serverProperties(server);

        sender = new CommandConsoleSender(server);

        this.pluginManager = new PluginManager(server);

        commandInfo = new HashMap<>();
        Register.getInstance().registerNativeCommand();
        registerNativeInfo();

        /**
         * 加载插件
         */
        this.plugins = PluginClassLoader.getClassLoader().loadPlugins(pluginDir);


        start();
    }

    public void start(){
        this.pool.execute(new ListenerThread());
        this.log.info("the listener thread is starting!!!!");
    }


    /**
     * 服务器监听线程启动
     */
    private class ListenerThread implements Runnable{
        @Override
        public void run() {
            Utils.TryCatch(()->{
                log.info("Listener-Thread:EXECUTOR_SERVICE->start");
                socket = getSocket(Integer.parseInt(serverProp.getProperty("port")));

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
                    //发出数据包部分由C#插件决定，C#插件的Server中央处理java发出的请求
                    //专门设立发包的api
                }
            });
        }
    }

    //TODO address和port通过数据包获取
    public void sendPacket(final DataPacket packet){
        Utils.TryCatch(()->{
            byte[] encode = packet.encode();
            DatagramPacket pack = new DatagramPacket(encode,encode.length,InetAddress.getByName(smod2Server.getAddress()),smod2Server.getPort());
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

    public File getServerFolder(){
        return serverfolder;
    }
    public static CommandConsoleSender getSender(){
        return sender;
    }

    public void help(){
        log.info("+================HELP========================+");
        Set<Map.Entry<String,String>> cmdSet = commandInfo.entrySet();
        for(Map.Entry<String,String> entry:cmdSet){
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.startsWith(PROP)){
                StringBuilder builder = new StringBuilder(value);
                value = builder.substring(PROP.length());
                value = lang.getProperty(value);
            }
            log.info(key+": "+value);
        }
    }


    public Map<String, String> getCommandInfo(){
        return commandInfo;
    }

    //监听Smod2转发端接口
    private DatagramSocket getSocket(int port) throws SocketException {

        DatagramSocket socket = new DatagramSocket(port);

        return socket;
    }

    /**
     * plugin manager
     */
    public PluginManager getPluginManager(){
        return pluginManager;
    }

    public void reboot(){
        Utils.TryCatch(()->{
            Jsmod2.startMessage(FileSystem.getFileSystem().langProperties(log));
            pluginManager.clear();
            pluginManager.getPluginClassLoader().loadPlugins(new File(PLUGIN_DIR));
        });
    }
    private void registerNativeInfo(){
        /*
         * prop:指向当前的lang文件
         */
        Set<Map.Entry<String,NativeCommand>> command = Register.getInstance().getNativeCommandMap().entrySet();
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

    public static Scanner getScanner(){
        return scanner;
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
}
