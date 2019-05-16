package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Role;

public class PlayerCheckEscapeEvent extends PlayerEvent {
    private boolean allowEscape;
    private Role changeRole;

    public PlayerCheckEscapeEvent(Player player) {
        super(player);
    }
}
