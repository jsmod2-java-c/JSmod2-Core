/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.Player;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.PacketSender;



/**
 * @author kevinj
 */
public class PlayerPocketDimensionExitEvent extends PlayerEvent implements IPlayerPocketDimensionExitEvent{
    private Vector exitPosition;

    public PlayerPocketDimensionExitEvent(Player player, Vector exitPosition) {
        super(player);
        this.exitPosition = exitPosition;
    }

    public PlayerPocketDimensionExitEvent(){

    }

    public Vector getExitPosition() {
        exitPosition = PacketSender.sendEventGetPacket(playerName,"ExitPosition", Vector.class);
        return exitPosition;
    }

    public void setExitPosition(Vector exitPosition) {
        PacketSender.sendEventSetPacket(playerName,"ExitPosition",exitPosition);
        this.exitPosition = exitPosition;
    }
}
