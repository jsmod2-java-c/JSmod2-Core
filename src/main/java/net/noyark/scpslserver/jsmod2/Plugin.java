package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;
import net.noyark.scpslserver.jsmod2.plugin.PluginClassLoader;

import java.io.File;

public interface Plugin {

    default void onLoad(){

    }

    default void onEnable(){

    }

    default void onDisable(){

    }

    void init(ILogger logger, Server server, String pluginName, File serverFile, String description, PluginClassLoader classLoader, File dataFolder);

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
}
