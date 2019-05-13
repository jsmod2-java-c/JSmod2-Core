package net.noyark.scpslserver.jsmod2;

import net.noyark.CommandSender;
import net.noyark.Message;
import net.noyark.scpslserver.jsmod2.command.NativeCommand;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.util.List;

import static org.fusesource.jansi.Ansi.Color.DEFAULT;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * @author magiclu550 #(code)jsmod2
 */

public class Console extends CommandSender {

    private Console(){
        super("CONSOLE");
    }

    private static Console console;

    static {
        console = new Console();
    }
    /**
     * 对于控制台的命令处理
     */

    public void commandInput(){
        while (true){
            Utils.getMessageSender().info(">");
            String command = Server.getScanner().nextLine();
            String[] strs = command.split(" ");
            String[] args = new String[strs.length-1];
            for(int i = 1;i<strs.length-1;i++){
                args[i] = strs[i];
            }
            List<NativeCommand> commands =
                        Server
                            .getSender()
                            .getServer()
                            .getPluginManager()
                            .getCommands();
            boolean find = false;
            /**
             * 级别
             * all
             * console
             * admin
             * player
             * nobody
             * noconsole
             *
             */
            for(NativeCommand cmd:commands){
                if(command.equals(cmd.getCommandName())){
                    if(!cmd.getPower().equals("noconsole"))
                        cmd.execute(this,args);
                    find = true;
                }
            }
            if(!find){
                Utils.getMessageSender().error("Unkown command,please input 'help'");
            }

        }
    }

    public static Console getConsole(){
        return console;
    }
}
