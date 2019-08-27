package cn.jsmod2;
import cn.jsmod2.core.Application;
import cn.jsmod2.core.Console;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.PluginClassLoader;
import cn.jsmod2.core.plugin.SpringContextUtil;
import cn.jsmod2.core.utils.Utils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.concurrent.CountDownLatch;

import static cn.jsmod2.core.utils.Utils.contains;

/**
 * -w -u -lr -lm -github -n -a
 * -w 打开web
 * -u 打开ui
 * -lr 打开round的log监听
 * -lm 打开multiAdmin和游戏的log监听
 * -github 打开和Github连接
 * -n 打开client处理
 * -a 打开全部
 */
@ServerApplication(DefaultServer.class)
public class ServerStarter {


    private class RPCHandler{

        private Sigar sigar = new Sigar();

        public void start(String sw){
            sw = sw.replace("1","-w")
                    .replace("2","-u")
                    .replace("3","-lr")
                    .replace("4","-lm")
                    .replace("5","-github")
                    .replace("6","-n")
                    .replace("7","-a");
            startNow(new String[]{sw});
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


    public void start(String[] args){

        //java -jar jsmod2.jar -rpc 20021
        //有-rpc就不允许其他命令

        if(contains(args,"-rpc")){
            //"-w -u -lr -lm -github -n -a"
            try {
                int port = Integer.parseInt(args[1]);
                WebServer webServer = new WebServer(port);
                PropertyHandlerMapping mapping = new PropertyHandlerMapping();
                mapping.addHandler("jsmod", RPCHandler.class);
                webServer.getXmlRpcServer().setHandlerMapping(mapping);
            }catch (XmlRpcException e){
                Utils.printException(e);
            }
        }else{
            startNow(args);
        }
    }

    private void startNow(String[] args){

        if(contains(args,"-a")){
            args[0] = "-w-u-lr-lm-n-github";
        }
        Utils.TryCatch(()->{
            CountDownLatch latch = new CountDownLatch(1);
            if(contains(args,"-w")) {
                new Thread(() -> {
                    SpringContextUtil.setApplicationContext(Starter.run(args));
                    latch.countDown();
                }).start();
                latch.await();
            }
            if(contains(args,"-u")) {
                CountDownLatch latch1 = new CountDownLatch(1);
                new Thread(() -> {
                    latch1.countDown();
                    try {
                        UIStarter.run(args);
                    } catch (Exception e) {
                        Utils.printException(e);
                    }
                }).start();
                latch1.await();
            }
            Application.run(this.getClass(),args);
        });
    }

}
