package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;

public class Scp096CooldownStartEvent extends PlayerEvent {
    private boolean allow;

    public Scp096CooldownStartEvent(Player player, boolean allow) {
        super(player);
        this.allow = allow;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }
}
