package cn.jsmod2.log;

import cn.jsmod2.utils.LogFormat;
import org.apache.log4j.Logger;

import static org.fusesource.jansi.Ansi.Color.RED;

public class ErrorLogger implements IErrorLogger {

    static Logger logger = Logger.getLogger(ServerLogger.class);

    @Override
    public void error(String message) {
        logger.error(LogFormat.format(message,"ERROR",RED)+"\n");
    }
}
