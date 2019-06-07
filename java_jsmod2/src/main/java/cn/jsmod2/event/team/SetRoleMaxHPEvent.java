/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.team;

import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.event.Event;

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
