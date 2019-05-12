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

    private static List<String> startInfo;

    private static List<String> successInfo;

    public static final String START = "start";


    static {
        startInfo = new ArrayList<>();
        registerStartInfo();
        successInfo = new ArrayList<>();
        registerSuccessInfo();
    }

    private static ILogger log = new ServerLogger();

    //
    // 选择语言部分->正常启动
    //

    public static void main(String[]args){
        try{
            //lang properties
            Properties langProperties = FileSystem.getFileSystem().langProperties(log);
            //plugin dir
            long start = System.currentTimeMillis();
            for(String info:startInfo){
                log.info(langProperties.getProperty(info));
            }
            new Server(log,langProperties);
            long startSuccess = System.currentTimeMillis();
            for(String success:successInfo){
                log.info(MessageFormat.format(langProperties.getProperty(success),(startSuccess-start)+""));
            }
            InputConsoleStream.commandInput();
        }catch (Exception e){
            log.error("this is a exception!!!");
            e.printStackTrace();
        }

    }

    public static void registerStartInfo(){
        startInfo.add(START+".info");
        startInfo.add(START+".thanks");
        startInfo.add(START+".warn");
        startInfo.add(START+".connect");
    }

    public static void registerSuccessInfo(){
        successInfo.add("start.finish");
    }
}
