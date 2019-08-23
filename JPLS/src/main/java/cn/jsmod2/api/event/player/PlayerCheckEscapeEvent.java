/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.Role;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;

/**
 * @author kevinj
 */

public class PlayerCheckEscapeEvent extends PlayerEvent implements IPlayerCheckEscapeEvent{
    private boolean allowEscape;
    private Role changeRole;

    public PlayerCheckEscapeEvent(Player player) {
        super(player);
    }

    public PlayerCheckEscapeEvent(){

    }

    public boolean isAllowEscape() {
        allowEscape = sendEventGetPacket(playerName,"AllowEscape",Boolean.class);
        return allowEscape;
    }

    public void setAllowEscape(boolean allowEscape) {
        sendEventSetPacket(playerName,"AllowEscape",allowEscape);
        this.allowEscape = allowEscape;
    }

    public Role getChangeRole() {
        changeRole = sendEventGetPacket(playerName,"ChangeRole",Role.class);
        return changeRole;
    }

    public void setChangeRole(Role changeRole) {
        sendEventSetPacket(playerName,"ChangeRole",changeRole);
        this.changeRole = changeRole;
    }
}
