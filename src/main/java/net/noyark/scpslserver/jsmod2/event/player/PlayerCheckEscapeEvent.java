package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Role;

/**
 * @author kevinj
 */

public class PlayerCheckEscapeEvent extends PlayerEvent {
    private boolean allowEscape;
    private Role changeRole;

    public PlayerCheckEscapeEvent(Player player) {
        super(player);
    }

    public PlayerCheckEscapeEvent(){

    }

    public boolean isAllowEscape() {
        return allowEscape;
    }

    public void setAllowEscape(boolean allowEscape) {
        this.allowEscape = allowEscape;
    }

    public Role getChangeRole() {
        return changeRole;
    }

    public void setChangeRole(Role changeRole) {
        this.changeRole = changeRole;
    }
}
