package cn.jsmod2.scpslserver.event.team;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.api.Role;
import cn.jsmod2.scpslserver.event.Event;

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
