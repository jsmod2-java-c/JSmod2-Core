/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.api.Component;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.protocol.map.generator.*;

import java.io.Serializable;

/**
 * @author magiclu550
 */

public class Generator extends ApiId implements Component,IGenerator, Serializable,Cloneable {

    private boolean open;
    private boolean locked;
    private boolean hasTablet;
    private boolean engaged;
    private float startTime;
    private float timeLeft;
    private Vector position;
    private Room room = new Room();

    public void unlock(){
        UnlockGeneratorPacket packet = new UnlockGeneratorPacket();
        packet.playerName = playerName;
        packet.send();
    }

    //这里未来解决
    public Object getComponent(){
          return null;
    }


    public boolean isOpen() {
        GetGeneratorOpenPacket packet = new GetGeneratorOpenPacket();
        packet.playerName = playerName;
        open = packet.send();
        return open;
    }

    public void setOpen(boolean open) {
        SetGeneratorOpenPacket packet = new SetGeneratorOpenPacket();
        packet.open = open;
        packet.playerName = playerName;
        packet.send();
        this.open = open;
    }

    public boolean isLocked() {
        GetGeneratorLockedPacket packet = new GetGeneratorLockedPacket();
        packet.playerName = playerName;
        locked = packet.send();
        return locked;
    }

    public boolean isHasTablet() {
        GetGeneratorHasTabletPacket packet = new GetGeneratorHasTabletPacket();
        packet.playerName = playerName;
        hasTablet = packet.send();
        return hasTablet;
    }

    public void setHasTablet(boolean hasTablet) {
        SetGeneratorHasTabletPacket packet = new SetGeneratorHasTabletPacket();
        packet.hasTablet = hasTablet;
        packet.playerName = playerName;
        packet.send();
        this.hasTablet = hasTablet;
    }

    public boolean isEngaged() {
        GetGeneratorEngagedPacket packet = new GetGeneratorEngagedPacket();
        packet.playerName = playerName;
        engaged = packet.send();
        return engaged;
    }


    public float getStartTime() {
        GetGeneratorStartTimePacket packet = new GetGeneratorStartTimePacket();
        packet.playerName = playerName;
        startTime = packet.send();
        return startTime;
    }


    public float getTimeLeft() {
        GetGeneratorTimeLeftPacket packet = new GetGeneratorTimeLeftPacket();
        packet.playerName = playerName;
        timeLeft = packet.send();
        return timeLeft;
    }

    public void setTimeLeft(float timeLeft) {
        SetGeneratorTimeLeftPacket packet = new SetGeneratorTimeLeftPacket();
        packet.playerName = playerName;
        packet.timeLeft = timeLeft;
        packet.send();
        this.timeLeft = timeLeft;
    }

    public Vector getPosition() {
        GetGeneratorPositionPacket packet = new GetGeneratorPositionPacket();
        packet.playerName = playerName;
        position = packet.send();
        return position;
    }


    public Room getRoom() {
        return room;
    }

}
