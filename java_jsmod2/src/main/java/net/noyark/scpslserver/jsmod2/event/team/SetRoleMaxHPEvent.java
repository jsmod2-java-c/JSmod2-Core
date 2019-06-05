package net.noyark.scpslserver.jsmod2.event.team;

import net.noyark.scpslserver.jsmod2.annotations.UseForServerInit;
import net.noyark.scpslserver.jsmod2.event.Event;
import net.noyark.scpslserver.jsmod2.utils.api.Role;

public class SetRoleMaxHPEvent extends Event {

    private Role role;

    private int maxHP;

    public SetRoleMaxHPEvent(Role role, int maxHP) {
        this.role = role;
        this.maxHP = maxHP;
    }

    public SetRoleMaxHPEvent() {

    }

    public Role getRole() {
        return role;
    }
    /** java-bean */
    @UseForServerInit
    public void setRole(Role role) {
        this.role = role;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
}
