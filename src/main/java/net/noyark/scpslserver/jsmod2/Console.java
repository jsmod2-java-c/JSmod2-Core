package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.command.NativeCommand;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * @author magiclu550 #(code)jsmod2
 */

public class Console extends Smod2Server {

    private Console(){
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
             * all 所有人
             * console 控制台
             * admin 管理员
             * player 玩家
             * nobody 任何人不可以
             * noconsole 控制台不可以
             *
             * 权限类型
             * 控制台可以操作的指令有以下权限
             * ！noconsole
             * 允许普通玩家使用的指令权限
             * player all
             */
            for(NativeCommand cmd:commands){
                if(command.equals(cmd.getCommandName())){
                    //如果含有这个权限
                    if(getPowers().contains(cmd.getPower())){
                        cmd.execute(this,args);
                    }else{
                        Utils.getMessageSender().error("you do not have this power");
                    }
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
