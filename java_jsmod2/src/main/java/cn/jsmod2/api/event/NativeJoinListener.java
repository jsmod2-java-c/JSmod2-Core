package cn.jsmod2.api.event;

import cn.jsmod2.core.annotations.EventManager;
import cn.jsmod2.core.annotations.NativeListener;
import cn.jsmod2.api.event.player.PlayerJoinEvent;
import cn.jsmod2.core.event.Listener;
import cn.jsmod2.core.log.ServerLogger;

@NativeListener
public class NativeJoinListener implements Listener {

    @EventManager
    public void onJoin(PlayerJoinEvent e){
        ServerLogger.getLogger().info(e.getPlayer().getName()+"Join the Server ["+e.getPlayer().getIpAddress()+"]");
    }

}
