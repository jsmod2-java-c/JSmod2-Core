/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;



import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;

/**
 * @author kevinj
 */

public class PlayerCallCommandEvent extends PlayerEvent implements IPlayerCallCommandEvent{
    private String returnMessage;
    private String command;

    public String getReturnMessage() {
        returnMessage = sendEventGetPacket(playerName,"ReturnMessage",String.class);
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        sendEventSetPacket(playerName,"ReturnMessage",returnMessage);
        this.returnMessage = returnMessage;
    }

    public String getCommand() {
        command = sendEventGetPacket(playerName,"Command",String.class);
        return command;
    }


    public PlayerCallCommandEvent(){

    }

}
