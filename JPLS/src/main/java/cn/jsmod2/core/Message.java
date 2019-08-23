/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;

import cn.jsmod2.core.utils.LogFormat;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

public class Message {


    public Message(){

    }

    public void info(String msg){
        System.out.print(msg);
    }
    public void normal(String msg,String out){
        System.out.println(ansi().eraseScreen().fg(Ansi.Color.GREEN).a(msg).fg(Ansi.Color.DEFAULT)+out);
    }
    public void newLine(){
        System.out.println();
    }

    public void error(String msg){
        System.out.println(LogFormat.textFormat(msg,RED));
    }

}
