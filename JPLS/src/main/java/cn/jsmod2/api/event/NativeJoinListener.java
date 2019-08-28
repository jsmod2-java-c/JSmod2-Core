package cn.jsmod2.api.event;

import cn.jsmod2.api.event.player.PlayerJoinEvent;
import cn.jsmod2.core.annotations.EventManager;
import cn.jsmod2.core.annotations.NativeListener;
import cn.jsmod2.core.event.Listener;
import cn.jsmod2.core.log.ServerLogger;

/**
 * 本地监听器，监听玩家进入的信息
 * @author magiclu550
 */
@NativeListener
public class NativeJoinListener implements Listener {

    @EventManager
    public void onJoin(PlayerJoinEvent e){
        ServerLogger.getLogger().info(e.getPlayer().getName()+"Join the Server ["+e.getPlayer().getIpAddress()+"]");
    }

}
