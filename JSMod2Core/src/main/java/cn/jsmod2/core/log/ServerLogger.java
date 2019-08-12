/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.log;

import cn.jsmod2.core.utils.LogFormat;
import cn.jsmod2.core.utils.LogType;
import cn.jsmod2.core.utils.Utils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.fusesource.jansi.Ansi.Color.*;



/**
 * implement ILogger
 *
 *  beautiful server logger
 * #jsmod2 java server
 *
 * 统一打印服务器消息
 *
 *
 * @see ILogger
 *
 * @see LogFormat
 *
 * @author magiclu550 #(code) for scpsl
 *
 * @author GNX-Susanoo
 *
 * @version jsmod 001
 *
 */


public class ServerLogger implements ILogger{
    private ConsoleOutputStream consoleOutputStream = new ConsoleOutputStream();
    @Deprecated
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private static ServerLogger log;

    private Logger logger = Logger.getLogger(ServerLogger.class);

    static {
        log = new ServerLogger();
    }
    @Override
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
        error(message,"");
    }
    @Override
    public void debug(String message) {
        debug(message,"");
    }
    @Override
    public void info(String message) {
        info(message,"");
    }
    @Override
    public void warn(String message) {
        warn(message,"");
    }

    @Override
    public void multiDebug(Class<?> clz, String message, String prefix, String suffix) {
        debug(message,prefix+getMultiMessage(clz)+"\t",suffix.equals("")?"\n":suffix);
    }

    @Override
    public void multiError(Class<?> clz, String message, String prefix, String suffix) {
        error(message,prefix+getMultiMessage(clz)+"\t",suffix.equals("")?"\n":suffix);
    }

    @Override
    public void multiInfo(Class<?> clz, String message, String prefix, String suffix) {
        info(message,prefix+getMultiMessage(clz)+"\t",suffix.equals("")?"\n":suffix);
    }

    @Override
    public void multiWarn(Class<?> clz, String message, String prefix, String suffix) {
        warn(message,prefix+getMultiMessage(clz)+"\t",suffix.equals("")?"\n":suffix);
    }
    @Override
    public void debug(String message, String prefix) {
        debug(message,prefix,"\n");
    }

    @Override
    public void error(String message, String prefix) {
        error(message,prefix,"\n");
    }

    @Override
    public void info(String message, String prefix) {
        info(message, prefix,"\n");
    }

    @Override
    public void warn(String message, String prefix) {
        warn(message, prefix,"\n");
    }

    @Override
    public void debug(String message, String prefix, String suffix) {
        String msg = LogFormat.format(message,"DEBUG",GREEN,prefix)+suffix;
        try {
            consoleOutputStream.write(getSimpleMessage(getSimpleMessage(msg)));
        } catch (IOException ignored) {
        }
        logger.debug(msg);
    }

    @Override
    public void error(String message, String prefix, String suffix) {
        String msg = LogFormat.format(message,"ERROR",RED,prefix,true)+suffix;
        try {
            consoleOutputStream.write(getSimpleMessage(msg));
        } catch (IOException ignored) {
        }
//        queue.offer(msg);
        logger.error(msg);
    }

    @Override
    public void info(String message, String prefix, String suffix) {
        String msg = LogFormat.format(message,"INFO",YELLOW,prefix)+suffix;
        try {
            consoleOutputStream.write(getSimpleMessage(msg));
        } catch (IOException ignored) {
        }
//        queue.offer(msg);
        logger.info(msg);
    }

    @Override
    public void warn(String message, String prefix, String suffix) {
        String msg = LogFormat.format(message,"WARN",RED,prefix,true)+suffix;
        try {
            consoleOutputStream.write(getSimpleMessage(msg));
        } catch (IOException ignored) {
        }
//        queue.offer(msg);
        logger.warn(msg);
    }

    public static ServerLogger getLogger() {
        return log;
    }

    @Deprecated
    public BlockingQueue<String> getQueue() {
        return queue;
    }

    public ConsoleOutputStream getConsoleOutputStream() {
        return consoleOutputStream;
    }

    private String getMultiMessage(Class clz){
        return LogFormat.textFormat("[",BLUE)+LogFormat.textFormat(getLine()+"",BLUE)+"]"+LogFormat.textFormat("["+ Utils.simpleClassName(clz) +"]", CYAN);
    }

    public int getLine(){
        Throwable t = new Throwable();
        return t.getStackTrace()[t.getStackTrace().length-1].getLineNumber();
    }

    public String getSimpleMessage(String str){
        return str.replaceAll("\\033\\[[0-9]+m","");
    }


}
