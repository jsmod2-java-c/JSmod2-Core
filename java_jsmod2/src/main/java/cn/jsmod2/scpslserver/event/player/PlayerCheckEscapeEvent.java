package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.utils.api.Role;
import cn.jsmod2.scpslserver.entity.Player;

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
