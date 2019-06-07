package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.log.ServerLogger;
import cn.jsmod2.log.ILogger;
import cn.jsmod2.utils.LogType;
import org.apache.log4j.Logger;
import org.junit.Test;


public class TestLog {

    private static Logger logger = Logger.getLogger(ServerLogger.class);
    @Test
    public void log(){

        ILogger logger = new ServerLogger();
        logger.log(LogType.DEBUG,"11");
        logger.log(LogType.INFO,"11");
    }

}
