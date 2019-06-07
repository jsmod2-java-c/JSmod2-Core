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
import cn.jsmod2.scpslserver.utils.player.DamageType;
import cn.jsmod2.scpslserver.utils.player.WeaponType;
import cn.jsmod2.scpslserver.utils.entity.Player;
import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author kevinj
 */
public class PlayerShootEvent extends PlayerEvent {
    private Player target;
    private DamageType weapon;
    private boolean shouldSpawnHitmarker;
    private boolean ShouldSpawnBloodDecal;
    private Vector SourcePosition;
    private Vector TargetPosition;
    private String TargetHitbox;
    private WeaponType WeaponSound;//????
    private Vector Direction;

    public boolean isShouldSpawnHitmarker() {
        return shouldSpawnHitmarker;
    }

    public void setShouldSpawnHitmarker(boolean shouldSpawnHitmarker) {
        this.shouldSpawnHitmarker = shouldSpawnHitmarker;
    }

    public boolean getShouldSpawnBloodDecal() {
        return ShouldSpawnBloodDecal;
    }

    public void setShouldSpawnBloodDecal(boolean shouldSpawnBloodDecal) {
        ShouldSpawnBloodDecal = shouldSpawnBloodDecal;
    }

    public Vector getDirection() {
        return Direction;
    }

    public void setDirection(Vector direction) {
        Direction = direction;
    }

    public Player getTarget() {
        return target;
    }

    public DamageType getWeapon() {
        return weapon;
    }

    public Vector getSourcePosition() {
        return SourcePosition;
    }

    public Vector getTargetPosition() {
        return TargetPosition;
    }

    public String getTargetHitbox() {
        return TargetHitbox;
    }


    /** just for javabean */
    @UseForServerInit
    public void setTarget(Player target) {
        this.target = target;
    }
    /** java-bean */
    @UseForServerInit
    public void setWeapon(DamageType weapon) {
        this.weapon = weapon;
    }

    public boolean isShouldSpawnBloodDecal() {
        return ShouldSpawnBloodDecal;
    }
    /** java-bean */
    @UseForServerInit
    public void setSourcePosition(Vector sourcePosition) {
        SourcePosition = sourcePosition;
    }
    /** java-bean */
    @UseForServerInit
    public void setTargetPosition(Vector targetPosition) {
        TargetPosition = targetPosition;
    }
    /** java-bean */
    @UseForServerInit
    public void setTargetHitbox(String targetHitbox) {
        TargetHitbox = targetHitbox;
    }

    public WeaponType getWeaponSound() {
        return WeaponSound;
    }
    public void setWeaponSound(WeaponType weaponSound) {
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
