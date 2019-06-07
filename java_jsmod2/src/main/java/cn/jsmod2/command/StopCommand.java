/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.command;

import cn.jsmod2.CommandSender;
import cn.jsmod2.Server;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class StopCommand extends NativeCommand {

    public StopCommand() {
        super("stop","console", "prop:cmd.stop");
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Server.getSender().getServer().close();
        return true;
    }
}
