package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class Scp096CooldownEndEvent extends PlayerEvent {
    private boolean allow;

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public Scp096CooldownEndEvent(Player player, boolean allow) {
        super(player);
        this.allow = allow;
    }
}
