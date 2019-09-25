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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static cn.jsmod2.core.FileSystem.*;
import static cn.jsmod2.core.utils.Utils.*;

/**
 * JSMod2是一款基于协议(JSmod2协议)开发的一款使得java插件可以开发SCPSL:SCP基金会秘密实验室
 * 插件的一个框架,这个框架主要由两部分组成
 * JPLS和JSmod2组成,JPLS的全称叫做Java Plugin Loading System,JSmod2称为Java Server Mod
 * Java Server Mod提供了开发插件的接口和工具类,JPLS则是驱动插件生效的核心,同时C#服务端必须装配
 * ProxyHandler(协议代理插件)才能使得java插件真正生效于指定的客户端
 * JSMod2同时提供了
 * JSMod2Manager:基于python开发的管理控制平台
 * JSMod2DevelopmentKit:JSMod2开发工具包,结合在JSmod2中
 * JSMod2 Repo:JSMod2的maven控制平台
 * http://repo.noyark.net/nexus
 * 同时JSMod2的源代码在github开放,并作为主要的版本控制平台
 * https://www.github.com/jsmod2-java-c
 * JSMod2的官方网站:
 * http://jsmod2.cn
 * 以上就是JSMod2的执行体系,即JSmod2的结构
 * |     这一部分都是JSmod2的组件    |
 * JSmod2->插件->JPLS->ProxyHandler->ServerMod->MultiAdmin/LocalAdmin->Game
 *
 * 首先上一届课讲解了开发一款JavaServerMod插件的流程,JSMod2已经提供了方便的插件加载框架
 * 并且提供了web网站开发的支持
 * <code>
 *
 *  @Controller
 *  public class Test{
 *
 *         @RequestMapping("/jsmod2")
 *         public String hello(){
 *             return "index.html";
 *         }
 *  }
 * </code>
 * 您完全可以在代码中使用这种形式进行开发网站,访问网站使用ip:jsmod2的默认web服务器端口/jsmod2即可
 *
 * 1.JSmod2的心脏: Server类和DefaultServer类
 * 这两个类是JSMod2的核心部分,一切能够让java和ServerMod交互,其实都是依靠了这个类/对象为基础,Server类
 * 和ServerMod的Server是不一样的
 *
 * - Server为基类,实现了IServer接口,IServer继承了Start和Closeable,Reloadable接口
 *      - Start主要包含了start方法(startWatch方法其实和start的作用几乎差不多),Closeable包含close方法,Reloadable包含reload方法
 *      - 分析Server的作用
 *          - Server的第一个作用,就是存储了这个服务端运行时的一切基本信息
 *              - 语言属性 lang
 *              - jar包的所在目录 serverfolder
 *              - 启动和成功的时间 startTime和startSuccessTime 启动时间就是(startSuccessTime-startTime)ms
 *              - 指令的基本信息
 *              - 一系列最常用的常量(在项目中使用频率最高)
 *              - 记录管理员信息的对象OpsFile
 *              - 使用的是udp还是tcp
 *              - 是否和游戏已经对接
 *          - Server的第二个作用,提供了这个服务端运行的基本对象
 *              - LineReader 命令行对象
 *              - RuntimeServer 正在运行的服务器对象
 *              - Logger 打印日志信息的对象
 *              - plugins 服务器的插件对象
 *              - PluginManager 服务器的插件管理对象
 *              - RegisterTemplate 注册机对象:就是管理一些字典信息的对象,用于运行过程根据情况获取,从而使得数据严谨整齐分区安放，比较易于管理
 *          - 第三个作用,运行监听线程:start和startWatch
 *              - 根据情况启动ListenerThread(TCP或者UDP)
 *                  - ListenerThread UDP
 *                  - ListenerThreadTCP TCP
 *                  - ListenerThread的作用是监听来自ProxyHandler的信息
 *                      - ProxyHandler由c#编写,后期将ProxyHandler会讲解
 *                      - ProxyHandler会在事件发生的时候发送数据包(EventPacket)，JSmod2的
 *                      监听线程会根据数据包id从Register中的registerEvents()中找到对应的class对象
 *                          例如 -> 0x01 -> 找到AdminQueryEvent.class 之后调用newInstance()产生事件对象
 *                          之后通过callEvent(传入一个Event对象)调用之前注册的监听器方法
 *                  - ListenerThread启动后,会将来自ProxyHandler的base64字符串解析成jsmod2协议的字符串(实际上是一个json字符串)
 *                      之后将json字符串拆分注入
 *
 *                      完整的事件jsmod2协议长这个样子:0x01-{}|||playerName:UUID序列|||admin-playerName:UUID序列
 *                      这里会把AdminQueryEvent中的playerName字段注入UUID序列(这个序列是为了从Server Mod中找到它所对应的对象)
 *                      然后从AdminQueryEvent中找到admin字段,是Player类型,然后Player类型中也有playerName字段,因此就把admin中的
 *                      playerName字段注入进去这个UUID序列(而且playerName和admin-playerName对应的uuid序列都是不同的)这样每个复杂对象
 *                      都有一个uuid对应,在proxyHandler中,uuid将会和一个对象绑定在一起
 *
 *                      此时ProxyHandler中的apiMapping就是这样的(在事件触发时就会把这两个信息放了进去,这里省略其他复杂对象)
 *                      {
 *                          "UUID1":"AdminQueryEvent对象1",
 *                          "UUID2":"admin对象1"
 *                          ...
 *                      }
 *
 *                      当在jsmod2中,使用这个admin
 *                      <code>
 *                          public void onAdmin(IAdminQueryEvent e){
 *                              e.getAdmin().getName();
 *                          }
 *                      </code>
 *                      此时getName发出了这个包
 *                      {
 *                          "id":"xxx"//具体id暂时不用知道
 *                          "field":"Name"
 *                          "playerName":"UUID2"
 *                      }
 *                      之后传输到ProxyHandler
 *                      ProxyHandler会先读取playerName找到UUID2
 *                      然后在通过UUID2,从apiMapping中找到admin对象1
 *                      之后admin对象1.Name获取返回值
 *                      然后通过JsonSetting对象把数据封装起来
 *                      之后返回
 *                      然后Socket此时进行write读取,然后通过BinaryStream的decode方法解码
 *                      得到Name的返回值
 *                      之后获取Name成功
 *                      这是事件触发和调用方法获取属性的运行流程,也是jsmod2的基本流程
 *                      游戏触发事件Event->之后把Event和它的复杂对象(如Player,TeamRole等等)放到apiMapping表里，并生成一个
 *                      UUID Key对应着他们,然后发出协议,把uuid注入到jsmod2的对象进去,之后对象在执行方法时候,会发出数据包,里面包含
 *                      了该对象的uuid,ProxyHandler接受到后,通过这个uuid找到游戏里的这个对象，从而实现对应的操作
 *
 *                      另外这些操作是jsmod2真正的核心部分，也是实现了jsmod2核心功能的地方
 *
 *                      实现这些解码的核心组件是BinaryStream,也就是协议的翻译器
 *
 * 2.JSMod2的大脑: BinaryStream
 *       - BinaryStream在JavaServerMod中是思考的角色，对一切传来的协议进行解析,再对一切发出的协议组合
 *       - Server运行过程中,PacketManager是协议管理器,协议管理器只是充当了控制者和决策者角色,就是我该根据什么情况去调用什么地方
 *          - 如调用事件和调用指令
 *          - 根据id去判别
 *              - Manager接口提供了已经实现的通过ID和数据包信息调用事件和指令的接口
 *          - PacketManager事实上代码很简单
 *              - 然后再就是callEventByPacket,
 *                  - 就是定义一个EventStream(BinaryStream的子类)
 *                  - 从events找到class对象
 *                  - 之后直接通过callEvent执行(callEvent如何执行的会在Plugin中讲解)
 *                  - 之后事件调用结束
 *              - 如果不存在这个id
 *                  - 那么就是关心命令调用
 *                  - 命令调用两种方式,c#控制台调用和玩家调用
 *                  - 这个是ProxyHandler发的id信息来判断的
 *                  - 最终从数据包中获取这个VO对象(Value Object）,其实就是把数据包信息拆解,组成的对象
 *                  - 最终获得到VO对象,就根据情况来执行,如果是控制台命令,那么就直接runConsoleCommand
 *                  - 如果不是,就获得到Player对象，然后传进去
 *              -   最后socket会返回一个信息0xFF&1，对对方说明已经结束了指令和事件,游戏可以继续进行下去(在事件没有结束前,ProxyHandler是将
 *                  事件给阻塞掉的)
 *          - Server的sendDataPacket 是对象调用时,如Player对象,就会使用这个方法(发出数据包),核心实现还是由BinaryStream实现
 *              - DataPacket是BinaryStream的子类
 *              - DataPacket分为 GetPacket和SetPacket(ControlPacket)和DoPacket以及SimplePacket(DoMethodPacket DoStream...)
 *              - DataPacket的介绍在Jsmod2-protocol[参见(1)]里说明了
 *              - JSmod2前期采用一个Packet对应一个Handler,但是作者突发奇想,写出了SimpleHandler,即通用Handler,通过"反射"机制实现的Handler
 *              可以动态的设置和获取数值,这个解析器可以10行代码顶n行
 *              - 对应SimpleHandler就是SimplePacket(Packet的定义在cn.jsmod2.network.protocol下)
 *          这些大概就是BinaryStream的作用
 *
 * 3.附带的组件
 *          - MultiAdminCommand 基于ProxyHandler的CommandHandler实现的,可以直接调用smod2上的命令
 *              multi HELP 查看smod2命令 multi 命令 参数1 参数2 调用smod2的命令
 *          - Config Framework 基于yaml json properties的Config框架,可以通过ConfigQueryer来定义对象(这样使用了对象池,节省内存开销)
 *
 *          - Oaml Config 这是为jsmod2定制的配置文件格式,位于oaml包下,可以从https://github.com/noyark-system/noyark_oaml_java来获得使用
 *          方法
 *
 *          - Plugin Loading Framework 是为JSmod2定制的基于URLClassLoader的插件加载框架,可以实现自动定义和自动注册的插件框架
 *              - 这个框架分为PluginClassLoader和PluginManager
 *                  - PluginClassLoader首先读取jar包,通过URLClassloader读取所有类对象，然后进行查找,如果找到配置文件则从plugin.yml读取信息,加载主类
 *                  如果没有配置文件,则找@Main注解,然后找到Main注解后开始加载Plugin对象,加载onLoad onEnable,在服务器停止前调用onDisable(强制停止则不会)
 *                  - 找到@Main后,会再尝试查找EnableRegister注解,如果没找到,则不进行自动注册,如果找到,则扫描全部实现Listener接口和继承Command对象的类,并
 *                  注册进去
 *                  - 注册Command只是将Plugin注入进去，然后放在commands映射表(PluginClassLoader中),如果是Listener,则将类进行拆分,整理出带@EventManager
 *                  的方法,之后下一步根据优先级整理成一个个列表,然后放在一个映射表中(首先根据方法的参数类型划分成一个个列表,再根据优先级对列表排序),之后发生事件时,
 *                  通过callEvent调用,callEvent则是先得到事件类型,然后把有和这个事件类型相同参数类型(或者子类的类型)的方法找到,然后根据优先级依次执行这些方法
 *                  <code>
 *                      public void onPlayerJoin(IPlayerJoinEvent e){
 *
 *                      }
 *                      //发送callEvent
 *                      Event e = PlayerJoinEvent.class.newInstance();//动态生成的事件对象
 *                      callEvent(e);//则会找到onPlayerJoin这个方法,之后执行
 *                      因此您可以通过这个特性来自定义事件
 *                  </code>
 *         - Emerald脚本语言
 *              - 基于java写的脚本语言,但是这个语言目前有点挫,但是可以结合命令行使用
 *
 *
 *
 *  参见(1)
 *           Jsmod2协议分为5种请求方式:Get，Set，IDSend，CommandRegister，CommandSender
 *             一种响应: Future响应
 * Jsmod2端会发送Get和Set和CommandRegister
 * Get请求发送后，会有一个具体的返回值，Get请求基本不会修改对方端的具体参数，它的目的仅仅为了返回值
 * Set请求发送后，不会有返回值，Set请求一定会修改对方端的具体参数，它的目的仅仅为了修改值
 * CommandRegister请求，用于注册命令，没有返回值
 * C#端会发送
 * IDSend是发送Event请求，当发送Event对象时，将全部对象添加到请求链，并附带一个id账号
 * CommandSender请求，用于执行jsmod2注册的命令
 *
 * Future响应是一个二进制的响应串，Jsmod2的Response对象已经封装了它，进行了响应编译
 *
 * Get和Set请求已经封装在了数据包中，直接使用send即可发包
 *
 * 一个Get请求
 * 111-{
 *    "id":"111",
 *    "type":"item",
 *    "field":"xxx",
 *    "player":"ADE4-FL09-ADGB-Y9E6“
 * }
 * 一个Set请求
 * 111-{
 *    "id":"111",
 *    "type":"item",
 *    "do":"remove",
 *    "player":"ADE4-FL09-ADGB-Y9E6“
 * }
 * 111-{
 *    "id":"111",
 *    "type":"item",
 *    "kinematic":"true",
 *    "player":"ADE4-FL09-ADGB-Y9E6“
 * }
 * IDSend请求
 * {
 *     #省略，对象信息
 * }|||"admin-playerName":"ADE4-FL09-ADGB-Y9E6"
 *
 *
 * Future响应
 * {
 *     #省略，对象信息
 * }|||"admin-item":"ADE4-FL09-ADGB-Y9E6"@!{
 *     #省略，对象信息
 * }|||"admin-item":"ADE4-FL09-ADGB-Y9E7"
 *
 * @author magiclu550
 */

public abstract class Server implements IServer {

    public static final String START = "start";

    private static final int MAX_LENGTH = 8*30;

    private static final String STOP = "end";

    private static final String PROP = "prop:";

    private static Scanner scanner = new Scanner(System.in);

    private static ConsoleReader lineReader;

    private static RuntimeServer runtimeServer;

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

    public volatile boolean isConnected;


    private AtomicInteger out;

    public Server(GameServer gServer,boolean useUDP) {

        Server.runtimeServer = new RuntimeServer(this);

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

        this.out = new AtomicInteger(0);

        try {
            this.chooseLangOrStart();

            ServerSocket socket = new ServerSocket(Integer.parseInt(serverProp.getProperty(Register.JSMOD2_ACCEPT_PORT)));

            socket.accept();

        }catch (Exception e){

        }

        this.isConnected = true;

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
            this.executeEmerald(args);
            this.start(args);
            this.successTime();
            scheduler.executeRunnable(()->Utils.TryCatch(this::startConsoleCommand));
        });
    }
    public void start(String[] args){
        if(useUDP) {
            this.pool.execute(new ListenerThread());
        }else{
            this.pool.execute(new ListenerThreadTCP());
        }
        if(contains(args,"-n")) {
            scheduler.executeRunnable(new NettyServer());
        }
        if(contains(args,"-lr")) {
            String log = logListener("yyyy-MM-dd HH.mm.ss", this::getMax, SMOD2_LOG_FILE);
            if (log != null) {
                this.pool.execute(new LogListener(log, Integer.parseInt(serverProp.getProperty(SMOD2_LOG_INTERVAL, "2000")), "yyyy-MM-dd HH.mm.ss", this::getMax, SMOD2_LOG_FILE));
            }
        }
        if(contains(args,"-lm")) {
            String log1 = logListener("yyyy-MM-dd_HH_mm", (format, x1, x2) -> getMultiSCPMax(format, x1, x2, "MA"), Register.CONSOLE_LOG);
            if (log1 != null) {
                this.pool.execute(new LogListener(log1, Integer.parseInt(serverProp.getProperty(SMOD2_LOG_INTERVAL, "2000")), "yyyy-MM-dd_HH_mm", (format, x1, x2) -> getMultiSCPMax(format, x1, x2, "MA"), Register.CONSOLE_LOG));
                this.pool.execute(new LogListener(log1, Integer.parseInt(serverProp.getProperty(SMOD2_LOG_INTERVAL, "2000")), "yyyy-MM-dd_HH_mm", (format, x1, x2) -> getMultiSCPMax(format, x1, x2, "SCP"), Register.CONSOLE_LOG));
            }
        }

        if(contains(args,"-github")) {
            if (Boolean.parseBoolean(serverProp.getProperty(GITHUB))) {
                this.pool.execute(new GithubConnectThread());
            }
        }

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
        close(true);
    }

    public void close(boolean trulyClose){
        closeAll();
        if(trulyClose) {
            System.exit(0);
        }
    }


    public Future sendData(byte[] encode,String ip,int port,boolean result) {
        Future future = new Result();
        out.addAndGet(1);
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
        return (double)count.get()/((double)(new Date().getTime()-startSuccessTime)/1000.0);
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

    public static RuntimeServer getRuntime(){
        return runtimeServer;
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

            count.addAndGet(1);

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

        }
    }
    private void closeStream(){
        Utils.TryCatch(()->{
            List<InputStream> oStreams = FileSystem.getFileSystem().getInputStreams();
            for(InputStream stream : oStreams){
                if(stream!=null)
                    stream.close();
            }
            List<OutputStream> iStreams = FileSystem.getFileSystem().getOutputStreams();
            for(OutputStream stream : iStreams){
                if(stream!=null)
                    stream.close();
            }
            List<BufferedReader> readers = FileSystem.getFileSystem().getReaders();
            for(BufferedReader reader:readers){
                if(reader!=null)
                    reader.close();
            }
            List<PrintWriter> writers = FileSystem.getFileSystem().getWriters();
            for(PrintWriter writer:writers){
                if(writer!=null)
                    writer.close();
            }
        });
    }

    private void closeAll(){
        isConnected = false;
        scheduler.getPool().shutdownNow();
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

    private AtomicInteger count = new AtomicInteger(0);
    private class ListenerThread implements Runnable{
        @Override
        public void run() {
            Utils.TryCatch(()->{
                //注意，一个jsmod2目前只支持一个smod2连接，不支持多个连接
                //在未来版本可能会加入支持多个smod2连接一个服务器
                serverSocket = getSocket(Integer.parseInt(serverProp.getProperty(FileSystem.THIS_PORT)));
                while (true) {
                    if(!Server.getRuntime().running().isConnected){
                        ServerLogger.getLogger().multiInfo(getClass(),"the listener thread is closed","","");
                    }
                    DatagramPacket request = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);
                    ((DatagramSocket)serverSocket).receive(request);
                    //manageMessage(request);
                    scheduler.executeRunnable(new PacketHandlerThread(request));
                    count.addAndGet(1);
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
                    if(!Server.getRuntime().running().isConnected){
                        ServerLogger.getLogger().multiInfo(getClass(),"the listener thread is closed","","");
                    }
                    //getLogger().multiDebug(getClass(),"正在响应...","","");
                    Socket socket = ((ServerSocket)serverSocket).accept();
                    scheduler.executeRunnable(new SocketHandlerThread(socket));
                    lock.lock();
                    count.addAndGet(1);
                    lock.unlock();
                    if (isDebug) {

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
                    if(!Server.getRuntime().running().isConnected){
                        ServerLogger.getLogger().multiInfo(getClass(),"the listener thread is closed","","");
                    }
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

    private void executeEmerald(String[] args){
        server.serverLogInfo("this cn.jsmod2.server uses the Emerald "+ Server.getRuntime().running().serverProp.getProperty(EMERALD_COMPILER,"java")+" compiler v0.1 Engine By MagicLu550");
        if(args.length!=0){
            try{
                for(String arg:args)
                    EmeraldScriptVM.getVM().importFile(arg);
                if(contains(args,"-emerald")){
                    System.exit(0);
                }
            }catch (Exception e){
                Utils.printException(e);
            }
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

    public AtomicInteger getIn() {
        return count;
    }

    public AtomicInteger getOut() {
        return out;
    }

    interface Max{


        int getMax(SimpleDateFormat format, File x1, File x2);

    }

}
