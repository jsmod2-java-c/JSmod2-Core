package cn.jsmod2.command;

import cn.jsmod2.CommandSender;
import cn.jsmod2.Console;
import cn.jsmod2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TabCommand extends NativeCommand {

    public TabCommand() {
        super("tab","prop:cmd.tab");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if(args.length !=0){
            Console.SimpleConsole console = Console.getSimpleConsole();
            List<CharSequence> list = new ArrayList<>();
            console.complete(args[0],0,list);
            int count = 0;
            for(CharSequence sq:list){
                Utils.getMessageSender().info(sq+"\t");
                if(count!=0&&(count%5==0)){
                   Utils.getMessageSender().newLine();
                }
                count++;
            }
            return true;
        }
        return false;
    }
}
