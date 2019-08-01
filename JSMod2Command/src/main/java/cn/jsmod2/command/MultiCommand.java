package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.log.ServerLogger;
import cn.jsmod2.core.protocol.MultiAdminCommand;

import java.util.List;

public class MultiCommand extends NativeCommand {

    public MultiCommand() {
        super("multi", "power", "use multiAdmin command");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        String[] str = new String[strings.length-1];
        System.arraycopy(strings,1,str,0,strings.length-1);
        List list = MultiAdminCommand.sendCommand(strings[0],str);
        for(Object o:list){
            ServerLogger.getLogger().multiInfo(getClass(),o.toString(),"","");
        }
        return true;
    }

}
