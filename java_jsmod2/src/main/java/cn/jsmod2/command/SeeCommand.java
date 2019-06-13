package cn.jsmod2.command;

import cn.jsmod2.CommandSender;
import cn.jsmod2.FileSystem;
import cn.jsmod2.utils.Utils;

import java.util.Set;

/**
 * 用于查看项目信息
 * see property
 * see help
 * see new property
 */
public class SeeCommand extends NativeCommand{

    public SeeCommand() {
        super("see", "CONSOLE", "prop:cmd.see");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if(args.length>=1){
            if(args[0].equals("help")){
                Set<Object> keys = FileSystem.getFileSystem().infoProperties().keySet();
                for(Object key:keys){
                    Utils.getMessageSender().info(key.toString()+"\t");
                }
                return true;
            }else if(args[0].equals("new")){
                if(args.length>=2) {
                    Utils.getMessageSender().info(FileSystem.getFileSystem().infoProperties(true).get(args[1]).toString());
                }
                return true;
            }else{
                Utils.getMessageSender().info(FileSystem.getFileSystem().infoProperties().get(args[0]).toString());
            }
        }
        return false;
    }
}
