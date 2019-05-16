package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class Player106TeleportEvent extends PlayerEvent {
    private Vector position;

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Player106TeleportEvent(Player player, Vector position) {
        super(player);
        this.position = position;
    }
}
