package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.impl.log.ServerLogger;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

import java.util.Properties;

public class Jsmod2 {

    private static ILogger log = new ServerLogger();

    //
    // 选择语言部分->正常启动
    //

    public static void main(String[]args){
        try{
            //lang properties
            Properties langProperties = FileSystem.LangProperties(log);
            long start = System.currentTimeMillis();
            log.info(langProperties.getProperty("start.info"));
            log.info(langProperties.getProperty("start.thanks"));
            log.warn(langProperties.getProperty("start.warn"));
            log.info(langProperties.getProperty("start.connect"));
            new Server(log,langProperties);
            long startSuccess = System.currentTimeMillis();
            log.info(langProperties.getProperty("start.finish").replace("{0}",(startSuccess-start)+""));
            InputConsoleStream.commandInput();
        }catch (Exception e){
            log.error("this is a exception!!!");
            e.printStackTrace();
        }

    }
}
