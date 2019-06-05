package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.entity.Player;
import cn.jsmod2.scpslserver.event.Event;

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

    /** java-bean */
    @UseForServerInit
    public void setPlayer(Player player) {
        this.player = player;
    }
}
