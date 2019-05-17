package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Generator;

/**
 * @author kevinj
 */

public class PlayerGeneratorEjectTabletEvent extends PlayerEvent {
    private Generator generator;
    private boolean allow;
    private boolean spawnTablet;

    public PlayerGeneratorEjectTabletEvent(Player player, Generator generator, boolean allow, boolean spawnTablet) {
        super(player);
        this.generator = generator;
        this.allow = allow;
        this.spawnTablet = spawnTablet;
    }

    public PlayerGeneratorEjectTabletEvent(){

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

    public boolean isSpawnTablet() {
        return spawnTablet;
    }

    public void setSpawnTablet(boolean spawnTablet) {
        this.spawnTablet = spawnTablet;
    }

    /** java-bean */
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

}
