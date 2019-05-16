package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.utils.api.Role;
import net.noyark.scpslserver.jsmod2.utils.player.DamageType;

public class PlayerSpawnRagdollEvent {
    private Role role;
    private Vector position;
    private Vector Rotation;
    private Player attacker;
    private DamageType damageType;
    private boolean allowRecall;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getRotation() {
        return Rotation;
    }

    public void setRotation(Vector rotation) {
        Rotation = rotation;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public boolean isAllowRecall() {
        return allowRecall;
    }

    public void setAllowRecall(boolean allowRecall) {
        this.allowRecall = allowRecall;
    }

    public Player getAttacker() {
        return attacker;
    }

    public PlayerSpawnRagdollEvent(Role role, Vector position, Vector rotation, Player attacker, DamageType damageType, boolean allowRecall) {
        this.role = role;
        this.position = position;
        Rotation = rotation;
        this.attacker = attacker;
        this.damageType = damageType;
        this.allowRecall = allowRecall;
    }
}
