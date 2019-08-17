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
import cn.jsmod2.network.protocol.event.admin.PlayerContain106Scp106sGetPacket;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;

/**
 * @author kevinj
 */

public class PlayerContain106Event extends PlayerEvent implements IPlayerContain106Event{
    private Player[] scp106s;
    private boolean activateContainment;

    //这个通过响应去做，获取数组，然后只获取一次
    public Player[] getScp106s() {
        if(scp106s == null){
            PlayerContain106Scp106sGetPacket packet = new PlayerContain106Scp106sGetPacket();
            packet.playerName = playerName;
            scp106s = (Player[]) packet.send();
        }
        return scp106s;
    }

    public boolean isActivateContainment() {
        activateContainment = sendEventGetPacket(playerName,"ActivateContainment",Boolean.class);
        return activateContainment;
    }

    public void setActivateContainment(boolean activateContainment) {
        sendEventSetPacket(playerName,"ActivateContainment",activateContainment);
        this.activateContainment = activateContainment;
    }


    public PlayerContain106Event(Player player, Player[] scp106s, boolean activateContainment) {
        super(player);
        this.scp106s = scp106s;
        this.activateContainment = activateContainment;
    }

    public PlayerContain106Event(){

    }
}
