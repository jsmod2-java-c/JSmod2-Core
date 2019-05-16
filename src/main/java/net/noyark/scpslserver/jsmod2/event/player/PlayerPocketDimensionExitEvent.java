package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

import java.util.Vector;

public class PlayerPocketDimensionExitEvent extends PlayerEvent {
    private Vector exitPosition;

    public PlayerPocketDimensionExitEvent(Player player, Vector exitPosition) {
        super(player);
        this.exitPosition = exitPosition;
    }
}
