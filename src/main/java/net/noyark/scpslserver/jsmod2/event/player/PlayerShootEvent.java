package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.player.DamageType;

public class PlayerShootEvent extends PlayerEvent {
    private Player target;
    private DamageType weapon;
    private boolean shouldSpawnHitmarker;
    private bool ShouldSpawnBloodDecal;
    private Vector SourcePosition;
    private Vector TargetPosition;
    private string TargetHitbox;
    private WeaponType WeaponSound;//????
    private Vector Direction;

    public boolean isShouldSpawnHitmarker() {
        return shouldSpawnHitmarker;
    }

    public void setShouldSpawnHitmarker(boolean shouldSpawnHitmarker) {
        this.shouldSpawnHitmarker = shouldSpawnHitmarker;
    }

    public bool getShouldSpawnBloodDecal() {
        return ShouldSpawnBloodDecal;
    }

    public void setShouldSpawnBloodDecal(bool shouldSpawnBloodDecal) {
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

    public string getTargetHitbox() {
        return TargetHitbox;
    }

    public PlayerShootEvent(Player player, Player target, DamageType weapon, boolean shouldSpawnHitmarker, bool shouldSpawnBloodDecal, Vector sourcePosition, Vector targetPosition, string targetHitbox, WeaponType weaponSound, Vector direction) {
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
}
