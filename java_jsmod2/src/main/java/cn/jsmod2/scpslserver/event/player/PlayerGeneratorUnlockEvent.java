/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.api.Generator;
import cn.jsmod2.scpslserver.utils.entity.Player;

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
