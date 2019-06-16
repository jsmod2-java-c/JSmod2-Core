/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.api.server.Smod2Server;
import cn.jsmod2.ex.ServerRuntimeException;
import cn.jsmod2.script.EmeraldScript_JavaParser;
import cn.jsmod2.script.Memory;
import cn.jsmod2.utils.Utils;
import jline.console.completer.Completer;
import cn.jsmod2.command.NativeCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author magiclu550 #(code)jsmod2
 */

public class Console extends Smod2Server {

    private Console(){
    }

    private static Console console;

    private static SimpleConsole simpleConsole;

    static {
        console = new Console();
        simpleConsole = console.new SimpleConsole();
    }
    /**
     * 对于控制台的命令处理
     */

    void commandInput() throws IOException{
        //ConsoleReader reader = Server.getLineReader();
        //reader.addCompleter(new SimpleConsole());
        while (true){
            //@Deprecated
            Utils.getMessageSender().info("\n>");
            String command = Server.getScanner().nextLine();
            //String command = reader.readLine(">");
            try{
                if(EmeraldScript_JavaParser.matchPattern(command)){
                    StringBuilder builder = new StringBuilder(command);
                    if(command.matches(Memory.matches.get("startfunc"))){
                        while(!builder.toString().endsWith(":end")){
                            Utils.getMessageSender().info("\njsmod2-func>");
                            String otherCommand = Server.getScanner().nextLine();
                            builder.append(otherCommand);
                        }
                    }
                    if(command.matches(Memory.matches.get("start"))){
                        while (!builder.toString().endsWith("}")){
                            Utils.getMessageSender().info("\njsmod2-func>");
                            String otherCommand = Server.getScanner().nextLine();
                            builder.append(otherCommand);
                        }
                    }
                    Utils.getMessageSender().info("RETURN_THAT:emerald."+EmeraldScript_JavaParser.parse(builder.toString()));
                }else{
                    runConsoleCommand(command);
                }
            }catch (Exception e){
                if(e instanceof ServerRuntimeException){
                    e.printStackTrace();

                }else{
                    try{
                        throw new ServerRuntimeException("the command have some problems,may no such command param",e);
                    }catch (ServerRuntimeException e1){
                        e1.printStackTrace();
                        e1.geteLogger().error("error, server exception");
                    }
                }
            }
        }
    }

    public static Console getConsole(){
        return console;
    }
    /** 自动补全的类 */
    public class SimpleConsole implements Completer{
        @Override
        public int complete(String s, int i, List<CharSequence> list) {
            List<String> entries =
                    Server
                            .getSender()
                            .getServer()
                            .getPluginManager()
                            .getCommands()
                            .stream()
                            .map(NativeCommand::getCommandName)
                            .filter(x->{
                                //模糊查询
                                char[] chars = s.toCharArray();
                                boolean[] founds = new boolean[chars.length];
                                int count = 0;
                                for(char cha:chars){
                                    founds[count] = x.contains(cha+"");
                                    count++;
                                }
                                return !Arrays.toString(founds).contains("false");
                            })
                            .collect(Collectors.toList());
            list.addAll(entries);
            return i-s.length();
        }
    }

    public static SimpleConsole getSimpleConsole() {
        return simpleConsole;
    }

    public boolean runConsoleCommand(String commandName,String[] args){
        args = EmeraldScript_JavaParser.setThat(args);
        for(int i = 0;i<args.length;i++){
            args[i] = EmeraldScript_JavaParser.getScript().executeFunction(args[i]).toString();
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
            if(commandName.equals(cmd.getCommandName())){
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
        return true;
    }

    public boolean runConsoleCommand(String command){
        String[] strs = command.split(" ");
        String[] args = new String[strs.length-1];
        System.arraycopy(strs,1,args,0,args.length);
        return runConsoleCommand(strs[0],args);
    }
}
