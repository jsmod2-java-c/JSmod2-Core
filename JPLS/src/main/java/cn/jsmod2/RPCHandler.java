package cn.jsmod2;

import cn.jsmod2.core.Console;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.PluginClassLoader;
import cn.jsmod2.core.utils.Utils;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
public class RPCHandler{

    private Sigar sigar = new Sigar();

    public void start(String sw){
        sw = sw.replace("1","-w")
                .replace("2","-u")
                .replace("3","-lr")
                .replace("4","-lm")
                .replace("5","-github")
                .replace("6","-n")
                .replace("7","-a");
        ServerStarter.getInstance().startNow(new String[]{sw});
    }

    public String get_status(){
        if(Server.getSender() == null)return "stop";
        return "started";
    }

    public void execute(String command){
        Console.getConsole().runConsoleCommandWithEmerald(command);
    }

    public double cpu() {
        try {
            return sigar.getCpuPerc().getCombined();
        }catch (SigarException e){
            Utils.printException(e);
        }
        return 0.0;
    }

    public double ram(){
        return 1.0-((double) Runtime.getRuntime().freeMemory()/(double)Runtime.getRuntime().totalMemory());
    }

    public String plugin_info(String info){
        return PluginClassLoader.getClassLoader().getPlugin_info().get(info);
    }


    public void stop(){
        if(Server.getSender() == null){
            ServerLogger.getLogger().multiWarn(getClass(),"the server has stopped","","");
            return;
        }
        Server.getSender().getServer().close();
    }

}