/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.utils;

import cn.jsmod2.scpslserver.utils.config.YamlConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigGetter {

    private static ConfigGetter configGetter;

    static {
        configGetter = new ConfigGetter();
    }

    private ConfigGetter(){
    }

    public static ConfigGetter getConfigGetter(){
        return configGetter;
    }

    public PluginFileVO toDoPluginSet(InputStream in) throws IOException {
        YamlConfig config = new YamlConfig("plugin.yml",false);
        Map propMap = (Map)config.get(".",in);
        String pluginName = propMap.get("name").toString();//pluginName
        String main_class = propMap.get("main").toString();//main_class
        String description = propMap.get("description").toString();//description
        String version = propMap.get("version").toString();//version
        return new PluginFileVO(pluginName,main_class,description,version);
    }
}
