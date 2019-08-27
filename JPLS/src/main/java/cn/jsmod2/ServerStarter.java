package cn.jsmod2;
import cn.jsmod2.core.Application;

import cn.jsmod2.core.annotations.ServerApplication;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.plugin.SpringContextUtil;
import cn.jsmod2.core.utils.Utils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static cn.jsmod2.core.utils.Utils.contains;

/**
 * -w -u -lr -lm -github -n -a
 *  * -w 打开web
 * -u 打开ui
 * -lr 打开round的log监听
 * -lm 打开multiAdmin和游戏的log监听
 * -github 打开和Github连接
 * -n 打开client处理
 * -a 打开全部
 */
@ServerApplication(DefaultServer.class)
public class ServerStarter {

    private static ServerStarter instance;

    public static final String P_KEY = "jsmod";
    //Failed to instantiate class cn.jsmod2.ServerStarter$RPCHandler
    public void start(String[] args){

        //java -jar jsmod2.jar -rpc 20021
        //有-rpc就不允许其他命令

        if(contains(args,"-rpc")){
            //"-w -u -lr -lm -github -n -a"
            try {
                instance = this;
                int port = Integer.parseInt(args[1]);
                ServerLogger.getLogger().multiInfo(getClass(),"xmlrpc is started...","","");
                WebServer webServer = new WebServer(port);
                PropertyHandlerMapping mapping = new PropertyHandlerMapping();
                mapping.addHandler(P_KEY, RPCHandler.class);
                webServer.getXmlRpcServer().setHandlerMapping(mapping);
                XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl)webServer.getXmlRpcServer().getConfig();
                serverConfig.setEnabledForExceptions(true);
                serverConfig.setContentLengthOptional(false);
                //
                try {
                    Method[] methods = RPCHandler.class.getDeclaredMethods();
                    XmlRpcHandlerMapping mapping1 = webServer.getXmlRpcServer().getHandlerMapping();
                    Field field = mapping1.getClass().getSuperclass().getDeclaredField("handlerMap");
                    field.setAccessible(true);
                    Method getNewMethod = mapping1.getClass().getSuperclass().getDeclaredMethod("newXmlRpcHandler",Class.class,Method[].class);
                    getNewMethod.setAccessible(true);
                    Map map = (Map) field.get(mapping1);
                    for(Method method:methods) {
                        map.put(P_KEY+"."+method.getName(),getNewMethod.invoke(mapping1,new Object[]{RPCHandler.class,new Method[]{method}}));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                //
                webServer.start();
            }catch (IOException|XmlRpcException e){
                Utils.printException(e);
            }
        }else{
            startNow(args);
        }
    }

    void startNow(String[] args){

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

    public static ServerStarter getInstance() {
        return instance;
    }
}
