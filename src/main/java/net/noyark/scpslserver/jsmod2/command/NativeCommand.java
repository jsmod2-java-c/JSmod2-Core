package net.noyark.scpslserver.jsmod2.command;

import net.noyark.scpslserver.jsmod2.CommandSender;

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

    public String getCommandName() {
        return commandName;
    }

    public String getPower() {
        return power;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean execute(CommandSender commandSender, String[] args);
}
