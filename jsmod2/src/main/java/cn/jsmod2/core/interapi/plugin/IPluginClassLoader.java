package cn.jsmod2.core.interapi.plugin;

import cn.jsmod2.core.plugin.Plugin;
import cn.jsmod2.core.utils.PluginFileVO;

import java.io.File;
import java.net.URLClassLoader;
import java.util.List;
import java.util.jar.JarFile;

public interface IPluginClassLoader {

    List<Plugin> loadPlugins(File pluginDir);

    void loadPlugin(String file);

    Plugin loadPlugin(File jar);

    void unloadPlugin(String name);

    Plugin loadPluginInfo(Object plugin, PluginFileVO vo, JarFile jarFile, URLClassLoader loader) throws Exception;

    List<Plugin> getPlugins();


}
