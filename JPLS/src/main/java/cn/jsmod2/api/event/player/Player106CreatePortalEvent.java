/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;


import cn.jsmod2.core.math.Vector;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;

/**
 * @author kevinj
 */

public class Player106CreatePortalEvent extends PlayerEvent implements IPlayer106CreatePortalEvent{
    private Vector position;

    public Vector getPosition() {
        position = sendEventGetPacket(playerName,"Position",Vector.class);
        return position;
    }

    public void setPosition(Vector position) {
        sendEventSetPacket(playerName,"Position",position);
        this.position = position;
    }


    public Player106CreatePortalEvent(){

    }
}
