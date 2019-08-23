/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.DamageType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.player.WeaponType;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */
public class PlayerShootEvent extends PlayerEvent implements IPlayerShootEvent{
    private Player target = new Player("");
    private DamageType weapon;
    private boolean shouldSpawnHitmarker;
    private boolean ShouldSpawnBloodDecal;
    private Vector SourcePosition;
    private Vector TargetPosition;
    private String TargetHitbox;
    private WeaponType WeaponSound;//????
    private Vector Direction;

    public boolean isShouldSpawnHitmarker() {
        shouldSpawnHitmarker = PacketSender.sendEventGetPacket(playerName,"ShouldSpawnHitmarker",Boolean.class);
        return shouldSpawnHitmarker;
    }

    public void setShouldSpawnHitmarker(boolean shouldSpawnHitmarker) {
        PacketSender.sendEventSetPacket(playerName,"ShouldSpawnHitmarker",shouldSpawnHitmarker);
        this.shouldSpawnHitmarker = shouldSpawnHitmarker;
    }

    public boolean getShouldSpawnBloodDecal() {
        shouldSpawnHitmarker = PacketSender.sendEventGetPacket(playerName,"ShouldSpawnBloodDecal",Boolean.class);
        return ShouldSpawnBloodDecal;
    }

    public void setShouldSpawnBloodDecal(boolean shouldSpawnBloodDecal) {
        PacketSender.sendEventSetPacket(playerName,"ShouldSpawnBloodDecal",shouldSpawnBloodDecal);
        ShouldSpawnBloodDecal = shouldSpawnBloodDecal;
    }

    public Vector getDirection() {
        Direction = PacketSender.sendEventGetPacket(playerName,"Direction",Vector.class);
        return Direction;
    }

    public void setDirection(Vector direction) {
        PacketSender.sendEventSetPacket(playerName,"Direction",direction);
        Direction = direction;
    }

    public Player getTarget() {
        return target;
    }

    public DamageType getWeapon() {
        weapon = PacketSender.sendEventGetPacket(playerName,"Weapon",DamageType.class);
        return weapon;
    }

    public Vector getSourcePosition() {
        SourcePosition = PacketSender.sendEventGetPacket(playerName,"SourcePosition",Vector.class);
        return SourcePosition;
    }

    public Vector getTargetPosition() {
        TargetPosition = PacketSender.sendEventGetPacket(playerName,"TargetPosition",Vector.class);
        return TargetPosition;
    }

    public String getTargetHitbox() {
        TargetHitbox = PacketSender.sendEventGetPacket(playerName,"TargetHitbox",String.class);
        return TargetHitbox;
    }


    public WeaponType getWeaponSound() {
        WeaponSound = PacketSender.sendEventGetPacket(playerName,"WeaponSound",WeaponType.class);
        return WeaponSound;
    }
    public void setWeaponSound(WeaponType weaponSound) {
        PacketSender.sendEventSetPacket(playerName,"WeaponSound",weaponSound);
        WeaponSound = weaponSound;
    }

    public PlayerShootEvent(Player player, Player target, DamageType weapon, boolean shouldSpawnHitmarker, boolean shouldSpawnBloodDecal, Vector sourcePosition, Vector targetPosition, String targetHitbox, WeaponType weaponSound, Vector direction) {
        super(player);
        this.target = target;
        this.weapon = weapon;
        this.shouldSpawnHitmarker = shouldSpawnHitmarker;
        ShouldSpawnBloodDecal = shouldSpawnBloodDecal;
        SourcePosition = sourcePosition;
        TargetPosition = targetPosition;
        TargetHitbox = targetHitbox;
        WeaponSound = weaponSound;
        Direction = direction;
    }

    public PlayerShootEvent(){

    }
}
