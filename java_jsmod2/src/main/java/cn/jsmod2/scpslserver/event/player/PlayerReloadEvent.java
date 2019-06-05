package cn.jsmod2.scpslserver.event.player;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.item.ItemType;
import cn.jsmod2.scpslserver.entity.Player;

/**
 * @author kevinj
 */

public class PlayerReloadEvent extends PlayerEvent {
    private ItemType weapon;
    private int ammoRemoved;
    private int ClipAmmoCountAfterReload;
    private int NormalMaxClipSize;
    private int CurrentClipAmmoCount;
    private int CurrentAmmoTotal;

    public PlayerReloadEvent(Player player, ItemType weapon, int ammoRemoved, int clipAmmoCountAfterReload, int normalMaxClipSize, int currentClipAmmoCount, int currentAmmoTotal) {
        super(player);
        this.weapon = weapon;
        this.ammoRemoved = ammoRemoved;
        ClipAmmoCountAfterReload = clipAmmoCountAfterReload;
        NormalMaxClipSize = normalMaxClipSize;
        CurrentClipAmmoCount = currentClipAmmoCount;
        CurrentAmmoTotal = currentAmmoTotal;
    }

    public int getAmmoRemoved() {
        return ammoRemoved;
    }

    public void setAmmoRemoved(int ammoRemoved) {
        this.ammoRemoved = ammoRemoved;
    }

    public int getClipAmmoCountAfterReload() {
        return ClipAmmoCountAfterReload;
    }

    public void setClipAmmoCountAfterReload(int clipAmmoCountAfterReload) {
        ClipAmmoCountAfterReload = clipAmmoCountAfterReload;
    }

    public ItemType getWeapon() {
        return weapon;
    }

    public int getNormalMaxClipSize() {
        return NormalMaxClipSize;
    }

    public int getCurrentClipAmmoCount() {
        return CurrentClipAmmoCount;
    }

    public int getCurrentAmmoTotal() {
        return CurrentAmmoTotal;
    }


    /** just do javabean */
    @UseForServerInit
    public void setWeapon(ItemType weapon) {
        this.weapon = weapon;
    }
    /** java-bean */
    @UseForServerInit
    public void setNormalMaxClipSize(int normalMaxClipSize) {
        NormalMaxClipSize = normalMaxClipSize;
    }
    /** java-bean */
    @UseForServerInit
    public void setCurrentClipAmmoCount(int currentClipAmmoCount) {
        CurrentClipAmmoCount = currentClipAmmoCount;
    }
    /** java-bean */
    @UseForServerInit
    public void setCurrentAmmoTotal(int currentAmmoTotal) {
        CurrentAmmoTotal = currentAmmoTotal;
    }
}
