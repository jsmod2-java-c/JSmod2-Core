package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.impl.log.ServerLogger;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class Jsmod2 {

    public static final String START = "start";


    static {
        Register.getInstance().registerStartInfo();
        Register.getInstance().registerSuccessInfo();
    }

    private static ILogger log = new ServerLogger();

    //
    // 选择语言部分->正常启动
    //

    public static void main(String[]args){
        try{
            //lang properties
            Properties langProperties = FileSystem.getFileSystem().langProperties(log);
            long start = System.currentTimeMillis();
            startMessage(langProperties);
            new Server(log,langProperties);
            long startSuccess = System.currentTimeMillis();
            for(String success:Register.getInstance().getSuccessInfo()){
                log.info(MessageFormat.format(langProperties.getProperty(success),(startSuccess-start)+""));
            }
            Console.getConsole().commandInput();
        }catch (Exception e){
            log.error("this is a exception!!!");
            e.printStackTrace();
        }
    }





    public static void startMessage(Properties langProperties){
        //plugin dir
        for(String info:Register.getInstance().getStartInfo()){
            log.info(langProperties.getProperty(info));
        }
    }
}
