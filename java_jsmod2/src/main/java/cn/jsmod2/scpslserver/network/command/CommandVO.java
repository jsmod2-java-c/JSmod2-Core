/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.network.command;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;

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
