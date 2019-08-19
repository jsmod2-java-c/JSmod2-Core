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
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;

import java.io.Serializable;

public class TeamRole extends ApiId implements Serializable,Cloneable,ITeamRole {

    private Team team;

    private Role role;

    private boolean roleDisallowed;

    private int maxHP;

    private String name;

    public Team getTeam() {
        SimpleGetStream stream = new SimpleGetStream(Team.class);
        team = stream.read(playerName,"Team",Team.class);
        return team;
    }

    public void setTeam(Team team) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"Team",team);
        this.team = team;
    }

    public Role getRole() {
        SimpleGetStream stream = new SimpleGetStream(Role.class);
        role = stream.read(playerName,"Role",Role.class);
        return role;
    }

    public void setRole(Role role) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"Role",role);
        this.role = role;
    }

    public boolean isRoleDisallowed() {
        SimpleGetStream stream = new SimpleGetStream(Boolean.class);
        roleDisallowed = stream.read(playerName,"RoleDisallowed",Boolean.class);
        return roleDisallowed;
    }

    public void setRoleDisallowed(boolean roleDisallowed) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"RoleDisallowed",roleDisallowed);
        this.roleDisallowed = roleDisallowed;
    }

    public int getMaxHP() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        maxHP = stream.read(playerName,"MaxHP",Integer.class);
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"MaxHP",maxHP);
        this.maxHP = maxHP;
    }

    public String getName() {
        SimpleGetStream stream = new SimpleGetStream(String.class);
        name = stream.read(playerName,"Name",String.class);
        return name;
    }

    public void setName(String name) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"name",name);
        this.name = name;
    }

    //这里未来解决
    public Object getTeamClass(){
        return null;
    }


}
