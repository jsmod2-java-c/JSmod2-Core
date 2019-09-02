package cn.jsmod2.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.command.NativeCommand;
import cn.jsmod2.core.plugin.PluginClassLoader;

public class LoadPluginCommand extends NativeCommand {

    public LoadPluginCommand() {
        super("load", Powers.CONSOLE,"prop:cmd.load");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        for(String str:strings){
            Server server = Server.getRuntime().running();
            PluginClassLoader loader = server.getPluginManager().getPluginClassLoader();
            String pluginFile = server.pluginDir.toString();
            loader.loadPlugin(pluginFile+"/"+str);
        }
        return true;
    }
}
