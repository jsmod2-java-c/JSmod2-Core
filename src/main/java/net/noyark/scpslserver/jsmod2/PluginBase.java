package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;
import net.noyark.scpslserver.jsmod2.plugin.PluginClassLoader;

import java.io.File;

/**
 * all of the plugin main-class must extends it
 *
 * @author magiclu550 #(code) jsmod2
 */


public abstract class PluginBase implements Plugin {

    private boolean haveInit = false;

    private Server server;

    private ILogger logger;

    private String pluginName;

    private boolean isEnabled;

    private File dataFolder;

    private File serverFile;

    private String description;

    private PluginClassLoader classLoader;

    public PluginBase(){
    }

    public void init(ILogger logger,Server server,String pluginName,File serverFile,String description,PluginClassLoader classLoader,File dataFolder){
        if(!haveInit){
            this.logger = logger;
            this.server = server;
            this.pluginName = pluginName;
            this.serverFile = serverFile;
            this.dataFolder = dataFolder;
            this.description = description;
            this.classLoader = classLoader;
            this.haveInit = true;
        }
    }

    public boolean isHaveInit() {
        return haveInit;
    }

    public Server getServer() {
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

    public PluginClassLoader getClassLoader() {
        return classLoader;
    }
}
