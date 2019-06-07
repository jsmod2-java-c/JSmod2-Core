/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.log;

import cn.jsmod2.utils.LogType;

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
