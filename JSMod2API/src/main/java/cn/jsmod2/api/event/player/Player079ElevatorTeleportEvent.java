/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.Elevator;
import cn.jsmod2.core.math.Vector;

import static cn.jsmod2.network.PacketSender.sendEventGetPacket;
import static cn.jsmod2.network.PacketSender.sendEventSetPacket;


/**
 * @author kevinj
 */

public class Player079ElevatorTeleportEvent extends PlayerEvent implements IPlayer079ElevatorTeleportEvent{
    private Vector camera;
    private Elevator elevator = new Elevator();
    private boolean allow;
    private float apDrain;

    public Vector getCamera() {
        camera = sendEventGetPacket(playerName,"Camera",Vector.class);
        return camera;
    }

    public Elevator getElevator() {
        return elevator;
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



    public Player079ElevatorTeleportEvent(){

    }
}
