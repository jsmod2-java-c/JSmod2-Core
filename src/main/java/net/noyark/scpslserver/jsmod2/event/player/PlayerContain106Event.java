package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class PlayerContain106Event extends PlayerEvent {
    private Player[] scp106s;
    private boolean activateContainment;

    public Player[] getScp106s() {
        return scp106s;
    }

    public boolean isActivateContainment() {
        return activateContainment;
    }

    public void setActivateContainment(boolean activateContainment) {
        this.activateContainment = activateContainment;
    }

    public PlayerContain106Event(Player player, Player[] scp106s, boolean activateContainment) {
        super(player);
        this.scp106s = scp106s;
        this.activateContainment = activateContainment;
    }
}
