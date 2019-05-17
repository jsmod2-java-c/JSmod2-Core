package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

/**
 * @author kevinj
 */

public abstract class PlayerEvent extends Event {
    private Player player;

    public PlayerEvent(Player player){
        this.player = player;
    }

    public PlayerEvent(){

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
