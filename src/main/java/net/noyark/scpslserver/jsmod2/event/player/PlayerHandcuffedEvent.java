package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerHandcuffedEvent extends PlayerEvent {
    public boolean Handcuffed;

    public Player Owner;

    public boolean isHandcuffed() {
        return Handcuffed;
    }

    public void setHandcuffed(boolean handcuffed) {
        Handcuffed = handcuffed;
    }

    public Player getOwner() {
        return Owner;
    }

    public void setOwner(Player owner) {
        Owner = owner;
    }

    public PlayerHandcuffedEvent(Player player, boolean handcuffed, Player owner) {
        super(player);
        Handcuffed = handcuffed;
        Owner = owner;
    }
}
