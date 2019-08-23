package cn.jsmod2.core.log;


import cn.jsmod2.core.utils.Utils;

import java.io.*;
import java.util.Properties;

/**
 * 自己写的logger，适配在jsmod2
 * log4j.xml
 */
public class DefaultLogger{

    private PrintWriter info;

    private PrintWriter error;

    private PrintWriter warn;

    private PrintWriter debug;

    private DefaultLogger(){

    }

    public static DefaultLogger getLogger(){
        try{
            DefaultLogger logger = new DefaultLogger();
            Properties properties = new Properties();
            properties.load(DefaultLogger.class.getClassLoader().getResourceAsStream("log4j.properties"));
            String[] files = {
                    properties.getProperty("log4j.appender.D.File"),
                    properties.getProperty("log4j.appender.E.File"),
                    properties.getProperty("log4j.appender.I.File"),
                    properties.getProperty("log4j.appender.W.File")
            };
            for (String file:files){
                File f = new File(file);
                if(!f.exists()){
                    String dir = file.substring(0,file.lastIndexOf("."));
                    File dirs = new File(dir);
                    if(!dirs.exists()){
                        dirs.mkdirs();
                    }
                    f.createNewFile();
                }
            }
            logger.debug = Utils.getWriter(new FileOutputStream(files[0]));
            logger.error = Utils.getWriter(new FileOutputStream(files[1]));
            logger.info = Utils.getWriter(new FileOutputStream(files[2]));
            logger.warn = Utils.getWriter(new FileOutputStream(files[3]));
            return logger;
        }catch (IOException e){
            throw new RuntimeException("no log4j.properties",e);
        }
    }

    public synchronized void error(Object message){
        System.out.println(message);
        error.println(message);
    }

    public synchronized void debug(Object message){
        System.out.println(message);
        debug.println(message);
    }

    public synchronized void warn(Object message){
        System.out.println(message);
        warn.println(message);
    }

    public synchronized void info(Object message){
        System.out.println(message);
        info.println(message);
    }

}
