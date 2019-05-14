package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;

public class PlayerEvent extends Event {
    private Player player;

    public PlayerEvent(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
