/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;


import cn.jsmod2.core.ex.ServerRuntimeException;
import cn.jsmod2.core.interapi.command.INativeCommand;
import cn.jsmod2.core.script.EmeraldScriptVM;
import cn.jsmod2.core.script.EnvPage;
import cn.jsmod2.core.script.Memory;
import cn.jsmod2.core.script.jni.EmeraldScript;
import cn.jsmod2.core.utils.Utils;
import jline.console.completer.Completer;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 控制台对象，可以处理控制台指令(NativeCommand和Command)
 * @author magiclu550 #(code)jsmod2
 */

public class Console extends CommandSender{

    private Console(){
        super("CONSOLE","all","console","cn.jsmod2.admin","cn.jsmod2.player","nobody");
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

    public void commandInput() throws IOException{
        //ConsoleReader reader = Server.getLineReader();
        //reader.addCompleter(new SimpleConsole());
        String out = Server.getSender().getServer().serverfolder+"/out/";
        File outDir = new File(out);
        if (!outDir.exists()){
            outDir.mkdirs();
        }
        File outFile = new File(out,"out.ela");
        if(!outFile.exists()){
            outFile.createNewFile();
        }
        PrintWriter stream = new PrintWriter(new FileOutputStream(outFile,true));
        while (true){
            //@Deprecated
            String command;
            if(Server.getSender().getServer().serverProp.getProperty(FileSystem.CONSOLE_LINE,"false").equals("false")) {
                Utils.getMessageSender().info("\n>");
                command = Server.getScanner().nextLine();
            }else{
                command = Server.getLineReader().readLine(">");
            }
            try{
                StringBuilder builder = runConsoleCommandWithEmerald(command);
                if(EnvPage.isWrite()){
                    stream.println(builder.toString());
                    stream.flush();
                }
            }catch (Exception e){
                if(e instanceof ServerRuntimeException){
                    Utils.printException(e);

                }else{
                    try{
                        throw new ServerRuntimeException("the command have some problems,may no such command param",e);
                    }catch (ServerRuntimeException e1){
                        Utils.printException(e1);
                        e1.geteLogger().multiError(getClass(),"error, cn.jsmod2.server exception","","");
                    }
                }
                stream.close();
            }
        }
    }

    public StringBuilder runConsoleCommandWithEmerald(String command){
        StringBuilder builder = new StringBuilder(command);
        if(EmeraldScriptVM.matchPattern(command)){

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

            String method = Server.getSender().getServer().serverProp.getProperty(FileSystem.EMERALD_COMPILER,"java");

            //目前java版属于最稳定版本，其他不建议使用
            if(method.equals("c++")){
                Utils.getMessageSender().info("RETURN_THAT:emerald."+ EmeraldScript.parse(builder.toString()));
            }else{
                Utils.getMessageSender().info("RETURN_THAT:emerald."+ EmeraldScript.java_parse(builder.toString()));
            }

        }else{
            runConsoleCommand(command);
        }
        return builder;
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
                            .map(INativeCommand::getCommandName)
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
        args = EmeraldScriptVM.getVM().setThat(EmeraldScriptVM.getVM().getVars(),args);
        for(int i = 0;i<args.length;i++){
            args[i] = EmeraldScriptVM.getVM().executeFunction(args[i], EmeraldScriptVM.getVM().getVars()).toString();
            if(args[i].startsWith("'")&&args[i].endsWith("'")){
                args[i] = args[i].substring(args[i].indexOf("'")+1,args[i].lastIndexOf("'"));
            }
        }
        List<INativeCommand> commands =
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
         * cn.jsmod2.admin 管理员
         * cn.jsmod2.player 玩家
         * nobody 任何人不可以
         * noconsole 控制台不可以
         *
         * 权限类型
         * 控制台可以操作的指令有以下权限
         * ！noconsole
         * 允许普通玩家使用的指令权限
         * cn.jsmod2.player all
         */
        for(INativeCommand cmd:commands){
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
            Utils.getMessageSender().error("Unknown command,please input 'help'");
        }
        return true;
    }

    @Override
    public void personalBroadcast(int duration, String message, boolean isMonoSpaced) {
        Utils.getMessageSender().info(message);
    }

    public boolean runConsoleCommand(String command){
        String[] strs = command.split(" ");
        String[] args = new String[strs.length-1];
        System.arraycopy(strs,1,args,0,args.length);
        return runConsoleCommand(strs[0],args);
    }
}
