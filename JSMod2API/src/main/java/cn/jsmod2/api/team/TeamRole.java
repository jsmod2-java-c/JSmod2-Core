/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.team;


import cn.jsmod2.core.ApiId;

import java.io.Serializable;

public class TeamRole extends ApiId implements Serializable,Cloneable,ITeamRole {

    private Team team;

    private Role role;

    private boolean roleDisallowed;

    private int maxHP;

    private String name;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isRoleDisallowed() {
        return roleDisallowed;
    }

    public void setRoleDisallowed(boolean roleDisallowed) {
        this.roleDisallowed = roleDisallowed;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //这里未来解决
    public Object getTeamClass(){
        return null;
    }


}
