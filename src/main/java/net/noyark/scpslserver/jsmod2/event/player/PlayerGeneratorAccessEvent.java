package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Generator;

public class PlayerGeneratorAccessEvent extends PlayerEvent {
    private Generator generator;
    private boolean allow;

    public PlayerGeneratorAccessEvent(Player player, Generator generator, boolean allow) {
        super(player);
        this.generator = generator;
        this.allow = allow;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public Generator getGenerator() {
        return generator;
    }
}
