package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.entity.Player;

/**
 * @author kevinj
 */

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

    /** java-bean */
    @UseForServerInit
    public void setScp106s(Player[] scp106s) {
        this.scp106s = scp106s;
    }

    public PlayerContain106Event(Player player, Player[] scp106s, boolean activateContainment) {
        super(player);
        this.scp106s = scp106s;
        this.activateContainment = activateContainment;
    }

    public PlayerContain106Event(){

    }
}
