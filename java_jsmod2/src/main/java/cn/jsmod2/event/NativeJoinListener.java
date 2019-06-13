package cn.jsmod2.event;

import cn.jsmod2.annotations.EventManager;
import cn.jsmod2.annotations.NativeListener;
import cn.jsmod2.event.player.PlayerJoinEvent;
import cn.jsmod2.log.ServerLogger;

@NativeListener
public class NativeJoinListener implements Listener {

    @EventManager
    public void onJoin(PlayerJoinEvent e){
        ServerLogger.getLogger().info(e.getPlayer().getName()+"Join the Server ["+e.getPlayer().getIpAddress()+"]");
    }

}
