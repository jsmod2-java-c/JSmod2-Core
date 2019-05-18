package net.noyark.scpslserver.jsmod2.network.command;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;

public class CommandVO {
    private String commandName;

    private String[] args;

    public CommandVO(String commandName, String[] args) {
        this.commandName = commandName;
        this.args = args;
    }

    public CommandVO(){

    }

    public String getCommandName() {
        return commandName;
    }

    @UseForServerInit
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String[] getArgs() {
        return args;
    }

    @UseForServerInit
    public void setArgs(String[] args) {
        this.args = args;
    }
}
