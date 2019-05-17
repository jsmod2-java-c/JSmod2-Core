package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Generator;

/**
 * @author kevinj
 */

public class PlayerGeneratorAccessEvent extends PlayerEvent {
    private Generator generator;
    private boolean allow;

    public PlayerGeneratorAccessEvent(Player player, Generator generator, boolean allow) {
        super(player);
        this.generator = generator;
        this.allow = allow;
    }

    public PlayerGeneratorAccessEvent(){

    }

    /** java-bean */
    public void setGenerator(Generator generator) {
        this.generator = generator;
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
