package cn.jsmod2.scpslserver.entity;

import cn.jsmod2.scpslserver.utils.api.Role;
import cn.jsmod2.scpslserver.utils.team.Team;

public class TeamRole {

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

    public Object getTeamClass(){
        return null;
    }
}
