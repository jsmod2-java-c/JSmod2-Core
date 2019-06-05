package cn.jsmod2.scpslserver.command;

import cn.jsmod2.scpslserver.CommandSender;

/**
 * The native command(server command)
 *
 * @author magiclu550 #(code) jsmod2
 */

public abstract class NativeCommand {

    private String commandName;

    private String power;

    private String description;

    public NativeCommand(String commandName,String power,String description){
        this.commandName = commandName;
        this.power = power;
        this.description = description;
    }
    public NativeCommand(String commandName,String description){
        this.commandName = commandName;
        this.power = "all";
        this.description = description;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getPower() {
        return power.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean execute(CommandSender commandSender, String[] args);
}
