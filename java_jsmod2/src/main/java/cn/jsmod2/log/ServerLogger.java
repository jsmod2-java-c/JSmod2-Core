/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.log;

import cn.jsmod2.utils.LogFormat;
import cn.jsmod2.utils.LogType;
import org.apache.log4j.Logger;

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
 * @see LogFormat
 *
 * @author magiclu550 #(code) for scpsl
 *
 * @version jsmod 001
 *
 */


public class ServerLogger implements ILogger{

    private static ServerLogger log;

    private Logger logger = Logger.getLogger(ServerLogger.class);

    static {
        log = new ServerLogger();
    }

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

    @Override
    public void error(String message) {
        logger.error(LogFormat.format(message,"ERROR",RED)+"\n");
    }

    public void debug(String message) {
        logger.debug(LogFormat.format(message,"DEBUG",GREEN)+"\n");
    }

    public void info(String message) {
        logger.info(LogFormat.format(message,"INFO",YELLOW)+"\n");
    }

    public void warn(String message) {
        logger.warn(LogFormat.format(message,"WARN",RED)+"\n");
    }

    public static ServerLogger getLogger() {
        return log;
    }
}
