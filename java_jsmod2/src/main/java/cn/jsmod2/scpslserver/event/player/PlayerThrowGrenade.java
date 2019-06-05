package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.item.ItemType;
import cn.jsmod2.scpslserver.entity.Player;
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
