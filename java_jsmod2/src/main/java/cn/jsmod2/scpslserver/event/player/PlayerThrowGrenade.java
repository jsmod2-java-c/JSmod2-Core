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
import cn.jsmod2.scpslserver.utils.item.ItemType;
import cn.jsmod2.scpslserver.utils.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author kevinj
 */
public class PlayerThrowGrenade extends PlayerEvent {

    private ItemType GrenadeType;
    private Vector direction;
    private boolean slowThrow;

    public PlayerThrowGrenade(Player player, ItemType grenadeType, Vector direction, boolean slowThrow) {
        super(player);
        GrenadeType = grenadeType;
        this.direction = direction;
        this.slowThrow = slowThrow;
    }

    public PlayerThrowGrenade(){

    }

    public ItemType getGrenadeType() {
        return GrenadeType;
    }

    /** java-bean */
    @UseForServerInit
    public void setGrenadeType(ItemType grenadeType) {
        GrenadeType = grenadeType;
    }

    public Vector getDirection() {
        return direction;
    }

    /** java-bean */
    @UseForServerInit
    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public boolean isSlowThrow() {
        return slowThrow;
    }

    /** java-bean */
    @UseForServerInit
    public void setSlowThrow(boolean slowThrow) {
        this.slowThrow = slowThrow;
    }


}
