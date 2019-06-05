package net.noyark.scpslserver.jsmod2;

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
        System.out.println(ansi().eraseScreen().fg(RED).a(msg).fg(DEFAULT));
    }

}
