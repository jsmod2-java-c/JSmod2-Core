package net.noyark.scpslserver.jsmod2.command;

import net.noyark.scpslserver.jsmod2.Plugin;

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
