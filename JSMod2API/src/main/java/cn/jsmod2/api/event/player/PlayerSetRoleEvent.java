/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.item.ItemType;
import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.api.team.TeamRole;

import java.util.List;

/**
 * @author kevinj
 */

public class PlayerSetRoleEvent extends PlayerEvent {
    private List<ItemType> items;
    private boolean usingDefaultItem;
    private Role role;
    private TeamRole teamRole;

    public void setItems(List<ItemType> items) {
        this.items = items;
    }

    public void setUsingDefaultItem(boolean usingDefaultItem) {
        this.usingDefaultItem = usingDefaultItem;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ItemType> getItems() {
        return items;
    }

    public boolean isUsingDefaultItem() {
        return usingDefaultItem;
    }

    public Role getRole() {
        return role;
    }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public PlayerSetRoleEvent(Player player, List<ItemType> items, boolean usingDefaultItem, Role role, TeamRole teamRole) {
        super(player);
        this.items = items;
        this.usingDefaultItem = usingDefaultItem;
        this.role = role;
        this.teamRole = teamRole;
    }

    public PlayerSetRoleEvent(){

    }
}
