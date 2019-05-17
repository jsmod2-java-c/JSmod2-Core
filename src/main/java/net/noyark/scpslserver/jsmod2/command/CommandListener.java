package net.noyark.scpslserver.jsmod2.command;

import net.noyark.scpslserver.jsmod2.Server;
import net.noyark.scpslserver.jsmod2.annotations.NativeListener;
import net.noyark.scpslserver.jsmod2.event.EventManager;
import net.noyark.scpslserver.jsmod2.event.Listener;
import net.noyark.scpslserver.jsmod2.event.player.PlayerCallCommandEvent;

@NativeListener
public class CommandListener implements Listener {

    //TODO 指令问题
    @EventManager
    public void handleCommand(PlayerCallCommandEvent event){
        String command = event.getCommand();

        Server.getSender().getServer().getPluginManager().excuteCommand(command,new String[2],event.getPlayer());
    }

}
