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

    private Map<Plugin, Map<String,ConfigSetting>> settings = new HashMap<>();

    private Map<String,Plugin> primarySettingsMap = new HashMap<>();

    private Map<String, List<Plugin>> secondary = new HashMap<>();

    public boolean registerConfig(PluginBase plugin,ConfigSetting setting){
        if(!this.settings.containsKey(plugin)){
            plugin.error("Trying to register a cn.jsmod2.config setting before the plugin registered with cn.jsmod2.config manager");
            return false;
        }
        Map<String,ConfigSetting> setting1 = this.settings.get(plugin);
        if(setting.isPrimary()){
            if(this.primarySettingsMap.containsKey(setting.getKey())){
                if(!this.primarySettingsMap.get(setting.getKey()).equals(plugin)){
                    plugin.info(plugin.getClass().getName()+" is trying to register as a primary cn.jsmod2.user of cn.jsmod2.config setting "+setting.getKey()+" this may cause some weird behaviour");
                }
            }else{
                this.primarySettingsMap.put(setting.getKey(),plugin);
            }
        }else{
            if(!this.secondary.containsKey(setting.getKey())){
                this.secondary.put(setting.getKey(),new ArrayList<>());
            }
            this.secondary.get(setting.getKey()).add(plugin);
        }
        if(!setting1.containsKey(setting.getKey())){
            setting1.put(setting.getKey(),setting);
        }else{
            plugin.warn(plugin.getClass().getName()+" is trying to register a duplicate setting: "+setting.getKey());
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
        ConfigSetting setting = this.resolvePrimary(key);
        return type.cast(setting.getValue());
    }

    public ConfigSetting resolvePrimary(String key){
        Plugin plugin = this.primarySettingsMap.get(key);
        return resolveSetting(plugin,key);
    }

    public ConfigSetting resolveSetting(Plugin plugin,String key){
        ConfigSetting setting = null;
        Map<String,ConfigSetting> dic = this.settings.get(plugin);
        return dic.get(key);
    }

    public boolean isRegistered(Plugin plugin,String key){
        boolean flag = false;
        if(this.primarySettingsMap.containsKey(key)){
            if(this.secondary.get(key).equals(plugin)){
                flag = true;
            }else if(this.secondary.containsKey(key) && this.secondary.get(key).contains(plugin)){
                flag = true;
            }
        }
        return flag;
    }

    public static ConfigManager getManager() {
        return manager;
    }
}
