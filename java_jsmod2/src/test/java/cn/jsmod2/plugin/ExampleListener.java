package cn.jsmod2.plugin;

import cn.jsmod2.api.event.player.PlayerJoinEvent;
import cn.jsmod2.api.event.team.SetRoleMaxHPEvent;
import cn.jsmod2.core.annotations.EventManager;
import cn.jsmod2.core.event.Listener;

public class ExampleListener implements Listener {

    @EventManager
    public void onPlayerJoin(PlayerJoinEvent e){

    }

    @EventManager
    public void OnRoleMaxHPEvent(SetRoleMaxHPEvent e){

    }

}
