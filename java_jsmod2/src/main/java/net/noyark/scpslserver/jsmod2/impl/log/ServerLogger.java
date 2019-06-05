package net.noyark.scpslserver.jsmod2.impl.log;

import net.noyark.scpslserver.jsmod2.utils.LogType;
import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;
import org.apache.log4j.Logger;

import static net.noyark.scpslserver.jsmod2.utils.LogFormat.format;
import static org.fusesource.jansi.Ansi.Color.*;



/**
 * implement ILogger
 *
 *  beautiful server logger
 * #jsmod2 java server
 *
 *
 * @see ILogger
 *
 * @see net.noyark.scpslserver.jsmod2.utils.LogFormat
 *
 * @author magiclu550 #(code) for scpsl
 *
 * @version jsmod 001
 *
 */


public class ServerLogger implements ILogger {


    private static Logger logger = Logger.getLogger(ServerLogger.class);


    public void log(LogType logType, String message) {
        Integer level = logType.getLevel();

        switch (level){
            //default
            case 0:
                System.out.println(message);
                break;
            //debug
            case 1:
                debug(message);
                break;
                //info
            case 2:
                info(message);
                break;
                //warn
            case 3:
                warn(message);
                break;
                //error
            case 4:
                error(message);
                break;
        }
    }

    public void debug(String message) {
        logger.debug(format(message,"DEBUG",GREEN)+"\n");
    }

    public void info(String message) {
        logger.info(format(message,"INFO",YELLOW)+"\n");
    }

    public void warn(String message) {
        logger.warn(format(message,"WARN",RED)+"\n");
    }

    public void error(String message) {
        logger.error(format(message,"ERROR",RED)+"\n");
    }
}
