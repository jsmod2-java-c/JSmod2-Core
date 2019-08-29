package cn.jsmod2;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.Console;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.PluginClassLoader;
import cn.jsmod2.core.utils.Utils;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//import cn.jsmod2.core.utils.Utils;
//import com.google.common.io.Resources;
//import org.hyperic.sigar.Sigar;
//import org.hyperic.sigar.SigarException;
//
//import java.io.File;

/**
 * 兼容启动客户端的RPC监听器,必须在-rpc 端口号下启动xmlrpc服务器，启动客户端可以
 * 通过这里的接口远程调用，目前支持启动,关闭,执行指令,查看从cpu使用率和ram监听器,以及
 * smod2服务器的一系列属性:最大人数和在线人数,和初始化操作:设置语言文件等
 * @author MagicLu550
 * @author junhe_fafa (about python client)
 * @since 1.0.0
 */
public class RPCHandler{

    /**
     * 心跳计时器，当心跳检测无返回超时过5s，服务器则自动关闭
     * 通过get_status进行心跳检测
     */
    private static AtomicInteger times = new AtomicInteger(0);

    /**
     * 可以用于开启服务端(即开启web,log监听器,数据包监听器,事件调度器和指令调度器)
     * 并开启心跳监听
     * @param sw 启动选项，以数字代替
     *           <code>
     *                     .replace("1","-w")
     *                     .replace("2","-u")
     *                     .replace("3","-lr")
     *                     .replace("4","-lm")
     *                     .replace("5","-github")
     *                     .replace("6","-n")
     *                     .replace("7","-a");
     *           </code>
     * @return "server has started"的启动成功信息，如果发生异常则会发生阻塞
     *          但发生异常几率几乎没有，除非发生 "玄学状态"
     */
    public String start(String sw){
        if(Server.getSender() == null){
            sw = sw.replace("1","-w")
                    .replace("2","-u")
                    .replace("3","-lr")
                    .replace("4","-lm")
                    .replace("5","-github")
                    .replace("6","-n")
                    .replace("7","-a");
            final String args = sw;
            new Thread(()->ServerStarter.getInstance().startNow(new String[]{args})).start();
            new Thread(()->{
                while (Server.getSender()!=null);
                try {
                    Thread.sleep(2000);
                    while (true) {
                        if(times.get() >= 5)stop();
                        times.addAndGet(1);
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    Utils.printException(e);
                }
            }).start();
            new Thread(()->{
               while (Server.getSender()!=null&&!Server.getSender().getServer().isConnected);
               try{
                   if(!Server.getSender().getServer().isConnected)ServerStarter.getInstance().getWebServer().shutdown();
               }catch (Exception e){
                   Utils.printException(e);
               }

            });
        }

        return "server has started";
    }

    /**
     * 用于设置语言文件,必须在start之前设置一个特定的语言(当然，如果已经设置过了，那就
     * 不用了),必须是已经存在的语言文件，如果不存在,则默认zh(中文语言)
     * @param lang 设置的语言属性
     * @return -1 说明设置的成功(设置不成功就崩了,但是和前面一样,"玄学状态"才会发生这种问题)
     */
    public int set_lang(String lang){
        String file = RPCHandler.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        file = file.substring(0,file.lastIndexOf("/")+1);
        String langFile = file+"/init.lang";
        Utils.TryCatch(()->FileUtils.write(new File(langFile),lang,"UTF-8"));
        return -1;
    }

    /**
     * 获得服务器的状态，用于心跳中，如果未启动则为stop，如果启动了未连接，则是disconnected,
     * 如果已经连接,返回connected
     * 顺便调用一次，心跳计数清零，重新记录时间
     * @return 启动状态 stop/connected/disconnected
     */
    public String get_status(){
        times.set(0);
        if(Server.getSender() == null)return "stop";
        if(Server.getSender().getServer().isConnected)return "connected";
        return "disconnected";
    }

    /**
     * 用于rpc过程中，远程执行服务器的命令,command为命令
     * 另外可以执行emerald脚本
     * @param command 命令代码
     * @return 命令的组合或者服务器关闭的消息
     */
    public String execute(String command){
        if(Server.getSender()==null){
            return "the server has stopped";
        }
        return Console.getConsole().runConsoleCommandWithEmerald(command)+"";
    }

    /**
     * 获取到目前为止的服务端启动时间，如果服务端没有启动，则返回-1
     * @return 启动的时间(ms)
     */
    public String get_uptime(){
        if(Server.getSender()==null){
            return "-1";
        }
        return (System.currentTimeMillis()-Server.getSender().getServer().getStartTime())+"";
    }

    /**
     * 获得cpu的占用率(正常计算下必须<1,虽然以下方法出现玄学的问题,cpu超过了10000,但是
     * 虽然前面失准，但是后面都是正常数值，这里做了处理，保证数值的准确的)
     * @return cpu的使用率
     */

    public double get_cpu() {
        double pre = CPUMonitorCalc.getInstance().getProcessCpu();
        while (pre >= 1){
            pre = CPUMonitorCalc.getInstance().getProcessCpu();
        }
        return pre;
    }

    /**
     * 获取jvm的ram使用率，这里的计算一般正常的，但是其在普通情况下波动频率较低,只有
     * 发生大规模数据包交互的时候会发生巨大的内存使用波动
     * @return ram的使用率
     */
    public double get_ram(){
        return 1.0-((double) Runtime.getRuntime().freeMemory()/(double)Runtime.getRuntime().totalMemory());
    }

    /**
     * 返回插件的介绍信息
     * @param info 对应的文件名，不包含之前的文件路径
     * @return 返回介绍信息
     */
    public String plugin_info(String info){
        String des = PluginClassLoader.getClassLoader().getPlugin_info().get(info);
        if(des == null){
            return "No such plugin";
        }
        return des;
    }

    /**
     * 可以关闭掉服务器，但是此时程序运行停止，接收会发生Connection refused之类的
     * 异常，捕捉即可，说明服务端关闭成功了
     * @return 服务器没启动过，返回false，启动了关闭就直接崩溃，没有返回值(理论上是true)
     */
    public boolean stop(){
        if(Server.getSender() == null){
            ServerLogger.getLogger().multiWarn(getClass(),"the server has stopped","","");
            return false;
        }
        Server.getSender().getServer().close();
        return true;
    }

    /**
     * 获取服务端的ip地址，如果关闭，则返回已经关闭
     * @return ip地址/已经关闭
     */
    public String get_ip(){
        if(Server.getSender()==null){
            return "the server has stopped";
        }
        return Server.getSender().getServer().getGameServer().getIpAddress();
    }

    /**
     * 获得服务器可以承载的最大玩家数量
     * @return 最大玩家数量
     */
    public int get_player_max(){
        if(Server.getSender()==null){
            return 0;
        }
        return Server.getSender().getServer().getGameServer().getMaxPlayers();
    }

    /**
     * 获取当前在线的玩家数量
     * @return 在线玩家数量
     */
    public int get_player(){
        if(Server.getSender()==null){
            return 0;
        }
        return Server.getSender().getServer().getGameServer().getPlayers().size();
    }

    /**
     * 获取全部在线玩家的姓名,并返回
     * @return 全部玩家姓名
     */
    public List<String> get_player_list(){
        List<String> list = new ArrayList<>();
        if(Server.getSender()==null)return list;
        List<? extends IPlayer> players = Server.getSender().getServer().getGameServer().getPlayers();
        for(IPlayer p:players){
            list.add(p.getName());
        }
        return list;
    }

    /**
     * 获取玩家的id信息
     * @param name 玩家名称
     * @return 对应玩家的id
     */
    public int get_player_id(String name){
        return this.getPlayer(name).getPlayerId();
    }

    /**
     * 获取玩家的steam id信息
     * @param name 玩家名称
     * @return 对应玩家的steam id
     */
    public String get_player_steam_id(String name){
        return this.getPlayer(name).getSteamId();
    }
    /**
     * 向玩家发送消息
     * @param name 玩家名称
     * @param message 信息内容
     * @return 玩家名称，如果存在返回Unknown
     */
    public String message(String name,String message){
        IPlayer player = getPlayer(name);
        player.personalBroadcast(0,message,true);
        return player.getName();
    }

    /**
     * 获取玩家的ip信息
     * @param name 玩家名称
     * @return 玩家的ip信息
     */

    public String get_player_ip(String name){
        return getPlayer(name).getIpAddress();
    }

    /**
     * 封禁指定玩家
     * @param name 玩家名称
     * @param time 封禁时间 分钟
     * @param cause 封禁原因
     * @return 玩家名称
     */

    public String player_ban(String name,int time,String cause){
        IPlayer player = getPlayer(name);
        player.ban(time,cause);
        return player.getName();
    }

    /**
     * 踢出指定玩家(杀死)
     * @param name 玩家名称
     * @param time
     * @return 玩家名称
     */
    public String player_kick(String name,int time){
        IPlayer player = getPlayer(name);
        player.kill();
        return player.getName();
    }

    /**
     * 获取玩家的身份信息
     * @param name 玩家名称
     * @return 身份信息
     */
    public String get_player_class(String name){
        return getPlayer(name).getTeamRole().getRole().toString();
    }

    /**
     * 通过指定名称获取玩家对象
     * @param name 玩家的名称
     * @return 玩家对象
     */
    private IPlayer getPlayer(String name){
        List<? extends IPlayer> players = Server.getSender().getServer().getGameServer().getPlayers();
        for(IPlayer player:players){
            if(player.getName().equals(name)){
                return player;
            }
        }
        return new Player("Unknown");
    }

    /**
     * 获取全部的handlers
     * @return 全部handlers的名字
     */
    public List<String> get_handlers(){
        List<String> strings = new ArrayList<>();
        Method[] methods = RPCHandler.class.getDeclaredMethods();
        for(Method method:methods){
            if(!Modifier.isPrivate(method.getModifiers()))
                strings.add(ServerStarter.P_KEY+"."+method.getName());
        }
        return strings;
    }

//    private Sigar initSigar() {
//        String os = System.getProperty("os.name");
//        try {
//            String file = Resources.getResource("sigar/.sigar_shellrc").getFile();
//            File classPath = new File(file).getParentFile();
//
//            String path = System.getProperty("java.library.path");
//            if (os.toLowerCase().contains("win")) {
//                path += ";" + classPath.getCanonicalPath();
//            } else {
//                path += ":" + classPath.getCanonicalPath();
//            }
//            System.setProperty("java.library.path", path);
//
//            return new Sigar();
//        } catch (Exception e) {
//            Utils.printException(e);
//            return null;
//        }
//    }


}


/**
 * 用于检测cpu使用率的工具类
 */
class CPUMonitorCalc {

    private static CPUMonitorCalc instance = new CPUMonitorCalc();

    private OperatingSystemMXBean osMxBean;
    private ThreadMXBean threadBean;
    private long preTime = System.nanoTime();
    private long preUsedTime = 0;

    private CPUMonitorCalc() {
        osMxBean = ManagementFactory.getOperatingSystemMXBean();
        threadBean = ManagementFactory.getThreadMXBean();
    }

    public static CPUMonitorCalc getInstance() {
        return instance;
    }

    public double getProcessCpu() {
        long totalTime = 0;
        for (long id : threadBean.getAllThreadIds()) {
                totalTime += threadBean.getThreadCpuTime(id);
        }
        long curtime = System.nanoTime();
        long usedTime = totalTime - preUsedTime;
        long totalPassedTime = curtime - preTime;
        preTime = curtime;
        preUsedTime = totalTime;
        return (((double) usedTime) / totalPassedTime / osMxBean.getAvailableProcessors()) * 100;
    }
}
