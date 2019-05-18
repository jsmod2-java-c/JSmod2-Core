package net.noyark.scpslserver.jsmod2.event.environment;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.utils.api.Role;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class ScpDeathAnnouncementEvent extends Event {
    private boolean shouldPlay;
    private Player deadPlayer;
    private Role playerRole;

    public boolean isShouldPlay() {
        return shouldPlay;
    }

    public void setShouldPlay(boolean shouldPlay) {
        this.shouldPlay = shouldPlay;
    }

    public Player getDeadPlayer() {
        return deadPlayer;
    }

    public Role getPlayerRole() {
        return playerRole;
    }

    public ScpDeathAnnouncementEvent(boolean shouldPlay, Player deadPlayer, Role playerRole) {
        this.shouldPlay = shouldPlay;
        this.deadPlayer = deadPlayer;
        this.playerRole = playerRole;
    }

    public ScpDeathAnnouncementEvent(){

    }

    /** java-bean */
    @UseForServerInit
    public void setDeadPlayer(Player deadPlayer) {
        this.deadPlayer = deadPlayer;
    }

    /** java-bean */
    @UseForServerInit
    public void setPlayerRole(Role playerRole) {
        this.playerRole = playerRole;
    }
}
