package cn.jsmod2;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.PluginClassLoader;
import cn.jsmod2.core.utils.Utils;
import com.google.common.io.Resources;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.io.File;

public class RPCHandler{

    private Sigar sigar = initSigar();

    public String start(String sw){
        sw = sw.replace("1","-w")
                .replace("2","-u")
                .replace("3","-lr")
                .replace("4","-lm")
                .replace("5","-github")
                .replace("6","-n")
                .replace("7","-a");
        final String args = sw;
        new Thread(()->ServerStarter.getInstance().startNow(new String[]{args})).start();
        return "server has started";
    }

    public String get_status(){
        if(Server.getSender() == null)return "stop";
        return "started";
    }


    public String execute(String command){
        if(Server.getSender()==null){
            return "the server has stopped";
        }
        return Server.getSender().getServer().getPluginManager().consoleExecuteCommand(command)+"";
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


    public boolean stop(){
        if(Server.getSender() == null){
            ServerLogger.getLogger().multiWarn(getClass(),"the server has stopped","","");
            return false;
        }
        Server.getSender().getServer().close();
        return true;
    }

    private Sigar initSigar() {
        String os = System.getProperty("os.name");
        try {
            String file = Resources.getResource("sigar/.sigar_shellrc").getFile();
            File classPath = new File(file).getParentFile();

            String path = System.getProperty("java.library.path");
            if (os.toLowerCase().contains("win")) {
                path += ";" + classPath.getCanonicalPath();
            } else {
                path += ":" + classPath.getCanonicalPath();
            }
            System.setProperty("java.library.path", path);

            return new Sigar();
        } catch (Exception e) {
            Utils.printException(e);
            return null;
        }
    }


}