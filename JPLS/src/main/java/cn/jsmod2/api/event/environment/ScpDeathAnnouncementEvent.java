/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.environment;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.api.team.Role;
import cn.jsmod2.core.event.Event;

import static cn.jsmod2.network.PacketSender.*;

/**
 * @author Kevinj
 * @author magiclu550
 */

public class ScpDeathAnnouncementEvent extends Event implements IScpDeathAnnouncementEvent{
    private boolean shouldPlay;
    private Player deadPlayer = new Player("");
    private Role playerRole;

    public boolean isShouldPlay() {
        shouldPlay = sendEventGetPacket(playerName,"ShouldPlay",Boolean.class);
        return shouldPlay;
    }

    public void setShouldPlay(boolean shouldPlay) {
        sendEventSetPacket(playerName,"ShouldPlay",shouldPlay);
        this.shouldPlay = shouldPlay;
    }

    public Player getDeadPlayer() {
        return deadPlayer;
    }

    public Role getPlayerRole() {
        playerRole = sendEventGetPacket(playerName,"PlayerRole",Role.class);
        return playerRole;
    }


    public ScpDeathAnnouncementEvent(){

    }


}
