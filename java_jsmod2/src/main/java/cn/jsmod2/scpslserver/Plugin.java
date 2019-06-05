package cn.jsmod2.scpslserver;

import cn.jsmod2.scpslserver.inferf.log.ILogger;
import cn.jsmod2.scpslserver.plugin.PluginClassLoader;

import java.io.File;

public interface Plugin {

    default void onLoad(){

    }

    default void onEnable(){

    }

    default void onDisable(){

    }

    void init(ILogger logger, Server server, String pluginName, File serverFile, String description, PluginClassLoader classLoader, File dataFolder, String version);

    boolean isHaveInit();

    Server getServer();

    ILogger getLogger();

    String getPluginName();

    boolean isEnabled();

    void setEnabled(boolean enabled);

    File getDataFolder();

    File getServerFile();

    String getDescription();

    PluginClassLoader getClassLoader();

    String getVersion();
}
