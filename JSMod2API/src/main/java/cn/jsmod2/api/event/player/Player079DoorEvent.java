/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.Door;


import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;


/**
 * @author kevinj
 * @author MagicLu550
 */

public class Player079DoorEvent extends PlayerEvent implements IPlayer079DoorEvent{
    private Door door = new Door();
    private boolean allow;
    private float apDrain;


    public Player079DoorEvent(){

    }

    public Door getDoor() {
        return door;
    }

    public boolean isAllow() {
        allow = sendEventGetPacket(playerName,"Allow",Boolean.class);
        return allow;
    }



    public void setAllow(boolean allow) {
        sendEventSetPacket(playerName,"Allow",allow);
        this.allow = allow;
    }

    public float getApDrain() {
        apDrain = sendEventGetPacket(playerName,"APDrain",Float.class);
        return apDrain;
    }

    public void setApDrain(float apDrain) {
        sendEventSetPacket(playerName,"APDrain",apDrain);
        this.apDrain = apDrain;
    }


}
