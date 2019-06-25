/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.core.Console;
import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.RegisterTemplate;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.ex.ServerRuntimeException;
import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.script.EmeraldScriptVM;
import cn.jsmod2.core.utils.LogFormat;
import org.fusesource.jansi.Ansi;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static cn.jsmod2.core.FileSystem.EMERALD_COMPILER;

/**
 * @author magiclu550 #(code) jsmod2
 */


public class Jsmod2 {

    public static final String START = "start";



    private static ILogger log = ServerLogger.getLogger();

    //
    // 选择语言部分->正常启动
    //

    public static void main(String[]args){
        if(args.length!=0){
            for(String arg:args)
                EmeraldScriptVM.parse(arg);
            return;
        }
        try{
            //lang properties
            Server server = new DefaultServer();
            Properties langProperties = FileSystem.getFileSystem().langProperties(log,server);
            server.setLang(langProperties);
            FileSystem.getFileSystem().initLang(langProperties);
            long start = System.currentTimeMillis();
            startMessage(langProperties,server);
            server.start();
            long startSuccess = System.currentTimeMillis();
            server.serverLogInfo("this server uses the Emerald "+ Server.getSender().getServer().serverProp.getProperty(EMERALD_COMPILER,"java")+" compiler v0.1 Engine By MagicLu550");
            for(RegisterTemplate template:server.getRegisters()) {
                for (String success : template.getSuccessInfo()) {
                    server.serverLogInfo(MessageFormat.format(langProperties.getProperty(success), (startSuccess - start) + ""));
                }
            }
            Console.getConsole().commandInput();
        }catch (Exception e){
            log.error("this is a exception!!!");
            if (e instanceof ServerRuntimeException){
                e.printStackTrace();
            }else{
                e.printStackTrace();
                try {
                    throw new ServerRuntimeException("Sorry,Unknown Exception", e);
                }catch (ServerRuntimeException e1){
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void startMessage(Properties langProperties,Server server){
        //plugin dir
        for(RegisterTemplate template:server.getRegisters()) {
            for (String info : template.getStartInfo()) {
                server.serverLogInfo(langProperties.getProperty(info));
            }
        }
    }


}
