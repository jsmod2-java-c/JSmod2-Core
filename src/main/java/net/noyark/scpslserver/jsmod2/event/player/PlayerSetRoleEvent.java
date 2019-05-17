package net.noyark.scpslserver.jsmod2.event.player;

import net.noyark.scpslserver.jsmod2.entity.Player;
import net.noyark.scpslserver.jsmod2.entity.TeamRole;
import net.noyark.scpslserver.jsmod2.utils.api.Role;
import net.noyark.scpslserver.jsmod2.utils.item.ItemType;

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
    /** java-bean */
    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
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
