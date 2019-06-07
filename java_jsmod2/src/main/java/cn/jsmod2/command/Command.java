/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.command;

import cn.jsmod2.Plugin;

/**
 * all command must extend it
 *
 * @author magiclu550 #(code) jsmod2
 */

public abstract class Command extends NativeCommand{

    private Plugin plugin;


    public Command(String commandName, String power, String description,Plugin plugin) {
        super(commandName, power, description);
        this.plugin = plugin;
    }

    public Command(String commandName,String description,Plugin plugin){
        super(commandName,description);
        this.plugin = plugin;
    }


    public Plugin getPlugin(){
        return plugin;
    }



}
