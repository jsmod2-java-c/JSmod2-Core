package net.noyark.scpslserver.jsmod2;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * @author magiclu550 #(code)jsmod2
 */

public class InputConsoleStream {

    public static void commandInput(){
        while (true){
            System.out.print(">");
            String command = Server.getScanner().nextLine();
            if(command.equals("stop")){
                Server.getSender().getServer().close();
            }if(command.equals("help")){
                Server.getSender().getServer().help();
            }else{
                System.out.println(ansi().eraseScreen().fg(RED).a("Unkown command,please input '/help'").fg(DEFAULT));
            }
        }
    }

}
