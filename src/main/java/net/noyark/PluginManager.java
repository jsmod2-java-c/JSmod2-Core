package net.noyark;

import net.noyark.scpslserver.jsmod2.Plugin;
import net.noyark.scpslserver.jsmod2.Server;
import net.noyark.scpslserver.jsmod2.command.Command;
import net.noyark.scpslserver.jsmod2.command.NativeCommand;
import net.noyark.scpslserver.jsmod2.ex.NoSuchPluginNameException;
import net.noyark.scpslserver.jsmod2.ex.PluginException;
import net.noyark.scpslserver.jsmod2.plugin.PluginClassLoader;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * the plugin manager
 *
 * @author magiclu550 #(code) jsmod2
 */

public class PluginManager {

    private Server server;

    public PluginManager(Server server){
        this.server = server;
    }

    private List<NativeCommand> commands = new ArrayList<>();


    public PluginClassLoader getPluginClassLoader(){
        return PluginClassLoader.getClassLoader();
    }

    public Plugin getPlugin(String pluginName){
        List<Plugin> plugins = getPluginClassLoader().getPlugins();
        for(Plugin plugin:plugins){
            if(plugin.getPluginName().equals(pluginName)){
                return plugin;
            }
        }
        throw new NoSuchPluginNameException(pluginName);
    }


    public List<Plugin> getPlugins(){
        return getPluginClassLoader().getPlugins();
    }

    public void registerCommand(Command command){
        Plugin plugin = command.getPlugin();
        if(plugin.isEnabled()){
            commands.add(command);
            server.getCommandInfo().put(command.getCommandName(),command.getDescription());
        }else{
            throw new PluginException("the plugin is not enabled");
        }
    }

    public void plugins(){
        List<Plugin> plugins = getPlugins();
        int i = 0;
        Utils.getMessageSender().info("plugins["+plugins.size()+"]:");
        for(Plugin plugin:plugins){
            Utils.getMessageSender().normal(plugin.getPluginName(),":"+plugin.getVersion());
            if(i%5==0){
                Utils.getMessageSender().newLine();
            }
        }
    }

    public List<NativeCommand> getCommands(){
        return commands;
    }
}
