package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerMakeNoiseEvent extends PlayerEvent {
    public PlayerMakeNoiseEvent(Player player) {
        super(player);
    }

    public PlayerMakeNoiseEvent(){

    }
}
