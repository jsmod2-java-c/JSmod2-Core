package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.api.Generator;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerGeneratorUnlockEvent extends PlayerEvent {
    private Generator generator;
    private boolean allow;

    public Generator getGenerator() {
        return generator;
    }

    /** java-bean */
    @UseForServerInit
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public boolean isAllow() {
        return allow;
    }

    public PlayerGeneratorUnlockEvent(Player player, Generator generator, boolean allow) {
        super(player);
        this.generator = generator;
        this.allow = allow;
    }

    public PlayerGeneratorUnlockEvent(){

    }
    public void setAllow(boolean allow) {
        this.allow = allow;
    }


}
