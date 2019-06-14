/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.ex.ServerRuntimeException;
import cn.jsmod2.log.ILogger;
import cn.jsmod2.log.ServerLogger;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author magiclu550 #(code) jsmod2
 */


public class Jsmod2 {

    public static final String START = "start";



    private static ILogger log = new ServerLogger();

    //
    // 选择语言部分->正常启动
    //

    public static void main(String[]args){
        try{
            //lang properties
            Properties langProperties = FileSystem.getFileSystem().langProperties(log);
            FileSystem.getFileSystem().initLang(langProperties);
            long start = System.currentTimeMillis();
            log.info("this server uses the Emerald v0.1 Engine By MagicLu550");
            startMessage(langProperties);
            new Server(langProperties);
            long startSuccess = System.currentTimeMillis();

            for(String success:Register.getInstance().getSuccessInfo()){
                log.info(MessageFormat.format(langProperties.getProperty(success),(startSuccess-start)+""));
            }
            Console.getConsole().commandInput();
        }catch (Exception e){
            log.error("this is a exception!!!");
            if (e instanceof ServerRuntimeException){
                e.printStackTrace();
            }else{
                try {
                    throw new ServerRuntimeException("Sorry,Unknown Exception", e);
                }catch (ServerRuntimeException e1){
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void startMessage(Properties langProperties){
        //plugin dir
        for(String info:Register.getInstance().getStartInfo()){
            log.info(langProperties.getProperty(info));
        }
    }
}
