package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

/**
 * all of the plugin main-class must extends it
 *
 */


public abstract class PluginBase implements Plugin {

    private boolean haveInit = false;

    private ILogger logger;

    public PluginBase(){
    }


    public void init(ILogger logger){
        if(!haveInit){
            this.logger = logger;
            this.haveInit = true;
        }
    }

}
