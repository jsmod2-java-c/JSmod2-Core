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
import org.fusesource.jansi.AnsiConsole;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static cn.jsmod2.core.utils.Utils.contains;

/**
 * 服务端的启动控制类,使用DefaultServer进行启动,args参数包含以下
 * <ul>
 *     <li>-w -u -lr -lm -github -n -a</li>
 *     <li>-w 打开web</li>
 *     <li>-u 打开ui</li>
 *     <li>-lr 打开round的log监听</li>
 *     <li>-lm 打开multiAdmin和游戏的log监听</li>
 *     <li>-github 打开和Github连接</li>
 *     <li>-n 打开client处理</li>
 *     <li>-a 打开全部</li>
 * </ul>
 * <P>同时包含了RPC心跳(-rpc port)和普通启动</P>
 * @author magiclu550
 */
@ServerApplication(DefaultServer.class)
public class ServerStarter {

    /**
     * 为了在Handler中可以被使用，在调用start时会被初始化
     */
    private static ServerStarter instance;

    /**
     * RPC的pKey名称
     */
    public static final String P_KEY = "jsmod";


    private WebServer webServer;
    /**
     * 通过此方法来判断是开启rpc模式还是直接启动，通过args里的-rpc是否存在来
     * 知晓，如果有-rpc参数，后面必须空格带着端口号，并且不得有其他的参数
     * @param args 启动参数
     */
    public void start(String[] args){



        //java -jar jsmod2.jar -rpc 20021
        //有-rpc就不允许其他命令

        if(contains(args,"-rpc")){
            //"-w -u -lr -lm -github -n -a"
            try {
                instance = this;
                int port = Integer.parseInt(args[1]);
                ServerLogger.getLogger().multiInfo(getClass(),"xmlrpc is started...","","");
                webServer = new WebServer(port);
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
            AnsiConsole.systemInstall();
            startNow(args);
        }
    }

    /**
     * 开启一个真正的服务器，使得各个监听线程打开
     * @param args 启动选项
     */
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

    /**
     * 获得ServerStarter对象
     * @return ServerStarter单例对象
     */
    public static ServerStarter getInstance() {
        return instance;
    }

    public WebServer getWebServer() {
        return webServer;
    }
}
