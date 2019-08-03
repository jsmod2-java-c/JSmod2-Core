package cn.jsmod2.core.interapi.plugin;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.event.Listener;
import cn.jsmod2.core.interapi.event.IEvent;
import cn.jsmod2.core.interapi.command.INativeCommand;
import cn.jsmod2.core.plugin.ConfigSetting;
import cn.jsmod2.core.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public interface IPluginManager {

    IPluginClassLoader getPluginClassLoader();

    Plugin getPlugin(String pluginName);

    List<Plugin> getPlugins();

    void registerCommand(Command command);

    void plugins();

    void registerEvents(final Listener listener, Plugin plugin);

    void registerEvent(final Method method, Plugin plugin);

    void registerNativeEvents(Listener listener);

    void callEvent(final IEvent event);

    List<INativeCommand> getCommands();

    List<INativeCommand> getPluginCommands();

    boolean executeCommand(String commandName, String[] args, CommandSender sender);

    boolean consoleExecuteCommand(String command);

    List<Listener> getAllListener();

    void clear();

    Map<Plugin, Map<String, ConfigSetting>> getSettings();


}
