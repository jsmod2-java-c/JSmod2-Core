package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.math.Vector;
import net.noyark.scpslserver.jsmod2.utils.item.ItemType;
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
