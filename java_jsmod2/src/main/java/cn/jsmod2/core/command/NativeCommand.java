/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.command;

import cn.jsmod2.core.CommandSender;

/**
 * 用于服务器自带指令的注册，该指令在重启服务器时不会被销毁，也不会
 * 新建，同时忽视了Plugin对象
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
