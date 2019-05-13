package net.noyark;

import net.noyark.scpslserver.jsmod2.Plugin;
import net.noyark.scpslserver.jsmod2.Server;
import net.noyark.scpslserver.jsmod2.command.Command;
import net.noyark.scpslserver.jsmod2.ex.NoSuchPluginNameException;
import net.noyark.scpslserver.jsmod2.plugin.PluginClassLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * the plugin manager
 *
 * @author magiclu550 #(code) jsmod2
 */

public class PluginManager {

    private Server server;

    private List<Command> commands = new ArrayList<>();



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

    public void registerCommand(Command command){
        commands.add(command);
        server.getCommandInfo().put(command.getCommandName(),command.getDescription());
    }

    public List<Command> getCommands(){
        return commands;
    }
}
