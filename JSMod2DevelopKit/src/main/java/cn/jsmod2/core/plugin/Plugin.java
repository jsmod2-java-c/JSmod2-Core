/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.plugin;

import cn.jsmod2.core.interapi.IServer;
import cn.jsmod2.core.interapi.plugin.IPluginClassLoader;
import cn.jsmod2.core.log.ILogger;

import java.io.File;


/**
 * 定义了一个插件对象相关的接口
 * @author magiclu550
 */
public interface Plugin {

    default void onLoad(){

    }

    default void onEnable(){

    }

    default void onDisable(){

    }

    void init(ILogger logger, IServer server, String pluginName, File serverFile, String description, IPluginClassLoader classLoader, File dataFolder, String version);

    boolean isHaveInit();

    IServer getServer();

    ILogger getLogger();

    String getPluginName();

    boolean isEnabled();

    void setEnabled(boolean enabled);

    File getDataFolder();

    File getServerFile();

    String getDescription();

    IPluginClassLoader getClassLoader();

    String getVersion();
}
