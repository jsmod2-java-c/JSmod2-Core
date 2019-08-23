package cn.jsmod2.core.interapi.command;

import cn.jsmod2.core.CommandSender;

public interface INativeCommand {

    String getCommandName();

    String getPower();

    String getDescription();

    boolean execute(CommandSender commandSender, String[] args);

}
