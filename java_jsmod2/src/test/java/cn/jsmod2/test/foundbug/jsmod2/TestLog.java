package cn.jsmod2.test.foundbug.jsmod2;

import cn.jsmod2.scpslserver.impl.log.ServerLogger;
import cn.jsmod2.scpslserver.inferf.log.ILogger;
import cn.jsmod2.scpslserver.utils.LogType;
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
