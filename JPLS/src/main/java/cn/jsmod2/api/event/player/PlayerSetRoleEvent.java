/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
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
import cn.jsmod2.network.PacketSender;
import cn.jsmod2.network.protocol.event.admin.PlayerSetRoleSetItemsPacket;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

import java.util.List;

/**
 * @author kevinj
 */

public class PlayerSetRoleEvent extends PlayerEvent implements IPlayerSetRoleEvent{
    private List<ItemType> items;
    private boolean usingDefaultItem;
    private Role role;
    private TeamRole teamRole;

    public void setItems(List<ItemType> items) {
        PlayerSetRoleSetItemsPacket packet = new PlayerSetRoleSetItemsPacket();
        packet.playerName = playerName;
        packet.items = items;
        packet.send();
        this.items = items;
    }

    public void setUsingDefaultItem(boolean usingDefaultItem) {
        PacketSender.sendEventSetPacket(playerName,"UsingDefaultItem",usingDefaultItem);
        this.usingDefaultItem = usingDefaultItem;
    }

    public void setRole(Role role) {
        PacketSender.sendEventSetPacket(playerName,"Role",role);
        this.role = role;
    }

    @SuppressWarnings("unchecked")
    public List<ItemType> getItems() {
        items = PacketSender.sendEventGetPacket(playerName,"Items",ItemType.class,List.class, GetTypes.GET_ARRAY);
        return items;
    }

    public boolean isUsingDefaultItem() {
        usingDefaultItem = PacketSender.sendEventGetPacket(playerName,"UsingDefaultItem",Boolean.class);
        return usingDefaultItem;
    }

    public Role getRole() {
        role = PacketSender.sendEventGetPacket(playerName,"Role",Role.class);
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
