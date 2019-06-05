package cn.jsmod2.scpslserver.inferf.log;

import cn.jsmod2.scpslserver.utils.LogType;

/**
 *
 * the interface of the logger
 *
 * use to print the message in console
 *
 * child: ServerLogger
 *
 * @author magiclu550 #(code) for scpsl
 * @version jsmod2 001
 * jsmod2 @noyark-sys
 */

public interface ILogger {

    void log(LogType logType, String message);

    void debug(String message);

    void info(String message);

    void warn(String message);

    void error(String message);


}
