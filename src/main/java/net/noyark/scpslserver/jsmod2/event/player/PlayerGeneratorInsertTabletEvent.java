package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Generator;

/**
 * @author kevinj
 */

public class PlayerGeneratorInsertTabletEvent extends PlayerEvent{
    private Generator generator;
    private boolean allow;
    private boolean removeTablet;

    public PlayerGeneratorInsertTabletEvent(Player player, Generator generator, boolean allow, boolean removeTablet) {
        super(player);
        this.generator = generator;
        this.allow = allow;
        this.removeTablet = removeTablet;
    }

    public PlayerGeneratorInsertTabletEvent(){

    }

    public Generator getGenerator() {
        return generator;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public boolean isRemoveTablet() {
        return removeTablet;
    }

    public void setRemoveTablet(boolean removeTablet) {
        this.removeTablet = removeTablet;
    }

    /** java-bean */
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}
