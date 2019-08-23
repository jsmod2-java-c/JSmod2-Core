/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.player.ExperienceType;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;

/**
 * @author kevinj
 * @author Magiclu550
 */

public class Player079AddExpEvent extends PlayerEvent implements IPlayer079AddExpEvent{
    private ExperienceType experienceType;
    private float expToAdd;

    public ExperienceType getExperienceType() {
        experienceType = sendEventGetPacket(playerName,"ExperienceType",ExperienceType.class);
        return experienceType;
    }

    public float getExpToAdd() {
        expToAdd = sendEventGetPacket(playerName,"ExpToAdd",Float.class);
        return expToAdd;
    }

    public void setExpToAdd(float expToAdd) {
        sendEventSetPacket(playerName,"ExpToAdd",expToAdd);
        this.expToAdd = expToAdd;
    }




    public Player079AddExpEvent(){

    }
}
