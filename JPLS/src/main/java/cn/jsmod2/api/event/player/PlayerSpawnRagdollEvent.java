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
import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.event.Event;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.PacketSender;

/**
 * @author kevinj
 */
public class PlayerSpawnRagdollEvent extends Event implements IPlayerSpawnRagdollEvent{
    private Role role;
    private Vector position;
    private Vector Rotation;
    private Player attacker;
    private DamageType damageType;
    private boolean allowRecall;

    public Role getRole() {
        role = PacketSender.sendEventGetPacket(playerName,"Role",Role.class);
        return role;
    }

    public void setRole(Role role) {
        PacketSender.sendEventSetPacket(playerName,"Role",role);
        this.role = role;
    }

    public Vector getPosition() {
        position = PacketSender.sendEventGetPacket(playerName,"Position",Vector.class);
        return position;
    }

    public void setPosition(Vector position) {
        PacketSender.sendEventSetPacket(playerName,"Position",position);
        this.position = position;
    }

    public Vector getRotation() {
        Rotation = PacketSender.sendEventGetPacket(playerName,"Rotation",Vector.class);
        return Rotation;
    }

    public void setRotation(Vector rotation) {
        PacketSender.sendEventGetPacket(playerName,"Rotation",Vector.class);
        Rotation = rotation;
    }

    public DamageType getDamageType() {
        damageType = PacketSender.sendEventGetPacket(playerName,"DamageType",DamageType.class);
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        PacketSender.sendEventSetPacket(playerName,"DamageType",damageType);
        this.damageType = damageType;
    }

    public boolean isAllowRecall() {
        allowRecall = PacketSender.sendEventGetPacket(playerName,"AllowRecall",Boolean.class);
        return allowRecall;
    }

    public void setAllowRecall(boolean allowRecall) {
        allowRecall = PacketSender.sendEventGetPacket(playerName,"AllowRecall",Boolean.class);
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

    public PlayerSpawnRagdollEvent(){

    }
}
