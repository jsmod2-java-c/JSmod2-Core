/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.utils;

import org.fusesource.jansi.Ansi;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * format the console log
 *
 * 可以给予控制台消息颜色
 *
 * @author magiclu550 #(code) for scpsl
 */

public class LogFormat {

    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss ");

    public static Ansi format(String message, String type, Ansi.Color color,String prefix){
        return ansi().eraseScreen().a(prefix).fg(MAGENTA).a(format.format(new Date())).fg(DEFAULT).a("[").fg(color).a(type).fg(DEFAULT).a("\t]").fg(BLUE).a(" "+message).reset();
    }

    public static Ansi textFormat(String message, Ansi.Color color){
        return ansi().eraseScreen().fg(color).a(message).reset();
    }
}
