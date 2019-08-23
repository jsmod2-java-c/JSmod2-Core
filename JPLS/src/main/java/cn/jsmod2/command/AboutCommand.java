package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.log.ServerLogger;

public class AboutCommand extends NativeCommand {

    public AboutCommand() {
        super("about", Powers.ADMIN, "see the cn.jsmod2.server version");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {

        try {
           commandSender.personalBroadcast(0,"Running " + FileSystem.getFileSystem().readInitPropertiesInfo().getProperty("jsmod2.about"),true);
        }catch (Exception e){
            ServerLogger.getLogger().error(e.getMessage());
            return false;
        }
        return true;
    }
}

