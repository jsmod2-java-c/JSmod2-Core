package net.noyark.scpslserver.jsmod2.command;

import net.noyark.ICommandSender;

/**
 * all command must extend it
 *
 * @author magiclu550 #(code) jsmod2
 */

public abstract class Command {

    private String commandName;

    private String power;

    private String description;

    public Command(String commandName, String power, String description) {
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

    public abstract boolean execute(ICommandSender commandSender,String[] args);

}
