/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.plugin;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ILogger;

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
