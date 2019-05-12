package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;

/**
 * all of the plugin main-class must extends it
 *
 * @author magiclu550 #(code) jsmod2
 */


public abstract class PluginBase implements Plugin {

    private boolean haveInit = false;

    private Server server;

    private ILogger logger;

    public PluginBase(){
    }

    public void init(ILogger logger,Server server){
        if(!haveInit){
            this.logger = logger;
            this.server = server;
            this.haveInit = true;
        }
    }

}
