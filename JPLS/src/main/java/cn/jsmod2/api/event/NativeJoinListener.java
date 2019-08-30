/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
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
