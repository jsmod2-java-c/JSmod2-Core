package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.interapi.event.IEvent;

public interface IPlayerEvent extends IEvent {

    IPlayer getPlayer();
}
