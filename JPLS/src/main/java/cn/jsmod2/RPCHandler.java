package cn.jsmod2;

import cn.jsmod2.core.Console;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.PluginClassLoader;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicInteger;
//import cn.jsmod2.core.utils.Utils;
//import com.google.common.io.Resources;
//import org.hyperic.sigar.Sigar;
//import org.hyperic.sigar.SigarException;
//
//import java.io.File;

public class RPCHandler{

    private static AtomicInteger times = new AtomicInteger(0);

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
                    e.printStackTrace();
                }
            }).start();
        }

        return "server has started";
    }

    public String get_status(){
        times.set(0);
        if(Server.getSender() == null)return "stop";
        return "started";
    }


    public String execute(String command){
        if(Server.getSender()==null){
            return "the server has stopped";
        }
        return Console.getConsole().runConsoleCommandWithEmerald(command)+"";
    }

    public double cpu() {
        double pre = CPUMonitorCalc.getInstance().getProcessCpu();
        while (pre >= 1){
            pre = CPUMonitorCalc.getInstance().getProcessCpu();
        }
        return pre;
    }

    public double ram(){
        return 1.0-((double) Runtime.getRuntime().freeMemory()/(double)Runtime.getRuntime().totalMemory());
    }

    public String plugin_info(String info){
        String des = PluginClassLoader.getClassLoader().getPlugin_info().get(info);
        if(des == null){
            return "No such plugin";
        }
        return des;
    }


    public boolean stop(){
        if(Server.getSender() == null){
            ServerLogger.getLogger().multiWarn(getClass(),"the server has stopped","","");
            return false;
        }
        Server.getSender().getServer().close();
        return true;
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
