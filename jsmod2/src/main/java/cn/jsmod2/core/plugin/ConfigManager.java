package cn.jsmod2.core.plugin;

import cn.jsmod2.core.log.ServerLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConfigManager 最新的ConfigManager
 * @author magiclu550
 * @since 1.0.1
 */
public class ConfigManager {

    private static ConfigManager manager;

    static {
        manager = new ConfigManager();
    }

    private Map<Plugin, Map<String,ConfigSetting>> settings = new HashMap<>();//记录全部的

    private Map<String,Plugin> primarySettingsMap = new HashMap<>();//插件单一的

    private Map<String, List<Plugin>> secondary = new HashMap<>();//插件不单一的

    public boolean registerConfig(PluginBase plugin,ConfigSetting setting){
        if(!this.settings.containsKey(plugin)){
            this.settings.put(plugin,new HashMap<>());
        }

        if(setting.isPrimary()){
            if(settings.containsKey(plugin)){
                plugin.warn("the primary key is primary");
                return false;
            }
            settings.get(plugin).put(setting.getKey(),setting);
            primarySettingsMap.put(setting.getKey(),plugin);
        }else{
            settings.get(plugin).put(setting.getKey(),setting);
            if(!secondary.containsKey(setting.getKey())){
                secondary.put(setting.getKey(),new ArrayList<>());
            }
            secondary.get(setting.getKey()).add(plugin);
        }
        return true;
    }

    public void unregisterConfig(Plugin plugin,String key){
        if(!this.settings.containsKey(plugin)){
            return;
        }
        this.settings.get(plugin).remove(key);
        if(!this.secondary.containsKey(key)||!this.secondary.get(key).remove(plugin)){
            this.primarySettingsMap.remove(key);
        }
    }

    public <T> T getConfig(Plugin plugin,String key,Class<T> type){
        if(!isRegistered(plugin,key)){
            ServerLogger.getLogger().multiWarn(getClass(),"Trying to access a cn.jsmod2.config setting that isn't registered to the plugin, this is bad practice.","","");
        }
        Object r = settings.get(plugin).get(key);
        if(r == null)return null;
        return type.cast(r);
    }


    public boolean isRegistered(Plugin plugin,String key){
        return settings.get(plugin).containsKey(key);
    }

    public static ConfigManager getManager() {
        return manager;
    }
}
