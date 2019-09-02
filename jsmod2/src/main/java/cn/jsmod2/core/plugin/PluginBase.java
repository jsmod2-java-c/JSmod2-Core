/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.plugin;

import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.event.Listener;
import cn.jsmod2.core.ex.ServerRuntimeException;
import cn.jsmod2.core.interapi.IServer;
import cn.jsmod2.core.interapi.plugin.IPluginClassLoader;
import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.utils.Utils;
import cn.jsmod2.core.utils.config.Config;
import cn.jsmod2.core.utils.config.ConfigQueryer;
import cn.jsmod2.core.utils.config.ConfigType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * all of the plugin main-classes must extend it
 *
 * @author magiclu550 #(code) jsmod2
 */


public abstract class PluginBase implements Plugin {


    private boolean haveInit = false;

    private IServer server;

    private ILogger logger;

    private String pluginName;

    private boolean isEnabled;

    private File dataFolder;

    private File serverFile;

    private String description;

    private IPluginClassLoader classLoader;

    private String version;

    public PluginBase(){
    }

    public void init(ILogger logger, IServer server, String pluginName, File serverFile, String description, IPluginClassLoader classLoader, File dataFolder, String version){
        if(!haveInit){
            this.logger = logger;
            this.server = server;
            this.pluginName = pluginName;
            this.serverFile = serverFile;
            this.dataFolder = dataFolder;
            this.description = description;
            this.classLoader = classLoader;
            this.version = version;
            this.haveInit = true;
        }
    }

    public boolean isHaveInit() {
        return haveInit;
    }

    public IServer getServer() {
        return server;
    }

    public ILogger getLogger() {
        return logger;
    }

    public String getPluginName() {
        return pluginName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled){
        if(!isEnabled){
            this.isEnabled = true;
            onEnable();
        }
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public File getServerFile() {
        return serverFile;
    }

    public String getDescription() {
        return description;
    }

    public IPluginClassLoader getClassLoader() {
        return classLoader;
    }

    public String getVersion(){
        return version;
    }

    public void initConfig(){
        Utils.TryCatch(()->{
            Config config = ConfigQueryer.getInstance(this.dataFolder+"/cn.jsmod2.config.yml",false, ConfigType.YAML);
            config.save();
        });
    }

    public Object addConfig(ConfigSetting setting){
        return ConfigManager.getManager().registerConfig(this,setting);
    }

    public <T> T getConfig(String key,Class<T> type){
       return type.cast(ConfigManager.getManager().getConfig(this,key).getValue());
    }


    public void info(String message){
        this.getServer().getLogger().multiInfo(this.getClass(),message,"","");
    }

    public void error(String message){
        this.getServer().getLogger().multiError(this.getClass(),message,"","");
    }

    public void debug(String message){
        this.getServer().getLogger().multiDebug(this.getClass(),message,"","");
    }

    public void warn(String message){
        this.getServer().getLogger().multiWarn(this.getClass(),message,"","");
    }

    public void registerEvents(Listener listener){
        this.getServer().getPluginManager().registerEvents(listener,this);
    }

    public void registerCommand(Command command){
        this.getServer().getPluginManager().registerCommand(command);
    }
}
