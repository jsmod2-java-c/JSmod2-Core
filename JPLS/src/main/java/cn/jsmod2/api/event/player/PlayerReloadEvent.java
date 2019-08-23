/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */

public class PlayerReloadEvent extends PlayerEvent implements IPlayerReloadEvent{
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

    public PlayerReloadEvent(){

    }

    public int getAmmoRemoved() {
        ammoRemoved = PacketSender.sendEventGetPacket(playerName,"AmmoRemoved",Integer.class);
        return ammoRemoved;
    }

    public void setAmmoRemoved(int ammoRemoved) {
        PacketSender.sendEventSetPacket(playerName,"AmmoRemoved",ammoRemoved);
        this.ammoRemoved = ammoRemoved;
    }

    public int getClipAmmoCountAfterReload() {
        ClipAmmoCountAfterReload = PacketSender.sendEventGetPacket(playerName,"ClipAmmoCountAfterReload",Integer.class);
        return ClipAmmoCountAfterReload;
    }

    public void setClipAmmoCountAfterReload(int clipAmmoCountAfterReload) {
        PacketSender.sendEventSetPacket(playerName,"ClipAmmoCountAfterReload",clipAmmoCountAfterReload);
        ClipAmmoCountAfterReload = clipAmmoCountAfterReload;
    }

    public ItemType getWeapon() {
        weapon = PacketSender.sendEventGetPacket(playerName,"Weapon",ItemType.class);
        return weapon;
    }

    public int getNormalMaxClipSize() {
        NormalMaxClipSize = PacketSender.sendEventGetPacket(playerName,"NormalMaxClipSize",Integer.class);
        return NormalMaxClipSize;
    }

    public int getCurrentClipAmmoCount() {
        CurrentClipAmmoCount = PacketSender.sendEventGetPacket(playerName,"CurrentClipAmmoCount",Integer.class);
        return CurrentClipAmmoCount;
    }

    public int getCurrentAmmoTotal() {
        CurrentAmmoTotal = PacketSender.sendEventGetPacket(playerName,"CurrentAmmoTotal",Integer.class);
        return CurrentAmmoTotal;
    }


}
