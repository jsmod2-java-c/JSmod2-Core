package net.noyark.test.foundbug.jsmod2;

import net.noyark.scpslserver.jsmod2.impl.log.ServerLogger;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;
import net.noyark.scpslserver.jsmod2.utils.LogType;
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
