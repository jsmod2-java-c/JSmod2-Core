package net.noyark.scpslserver.jsmod2.utils;

import org.fusesource.jansi.Ansi;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * format the console log
 *
 * @author magiclu550 #(code) for scpsl
 */

public class LogFormat {

    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss ");

    public static Ansi format(String message, String type, Ansi.Color color){
        return ansi().eraseScreen().fg(MAGENTA).a(format.format(new Date())).fg(DEFAULT).a("[").fg(color).a(type).fg(DEFAULT).a("\t]").fg(BLUE).a(" "+message).reset();
    }
}
