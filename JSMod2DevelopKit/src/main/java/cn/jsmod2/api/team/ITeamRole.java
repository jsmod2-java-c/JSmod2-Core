package cn.jsmod2.api.team;

public interface ITeamRole {

    Team getTeam();

    void setTeam(Team team);

    Role getRole();

    void setRole(Role role);

    boolean isRoleDisallowed();

    void setRoleDisallowed(boolean roleDisallowed);

    int getMaxHP();

    void setMaxHP(int maxHP);

    String getName();

    void setName(String name);

    Object getTeamClass();
}
