package cn.jsmod2.plugin;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.plugin.Plugin;

public class ExmapleCommand extends Command {

    public ExmapleCommand( Plugin plugin) {
        super("hello", "CONSOLE","hello,world", plugin);
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        return false;
    }
}
