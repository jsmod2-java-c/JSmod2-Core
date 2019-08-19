/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.api.Component;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.protocol.map.elevator.*;

import java.io.Serializable;
import java.util.List;

public class Elevator extends ApiId implements Component,IElevator, Serializable,Cloneable {

    private ElevatorType elevatorType;

    private ElevatorStatus elevatorStatus;

    private boolean locked;

    private boolean lockable;

    private float movingSpeed;


    public ElevatorType getElevatorType() {
        GetElevatorTypePacket packet = new GetElevatorTypePacket();
        packet.playerName = playerName;
        elevatorType = packet.send();
        return elevatorType;
    }


    public ElevatorStatus getElevatorStatus() {
        GetElevatorStatusPacket packet = new GetElevatorStatusPacket();
        packet.playerName = playerName;
        elevatorStatus = packet.send();
        return elevatorStatus;
    }


    public boolean isLocked() {
        GetElevatorLockedPacket packet = new GetElevatorLockedPacket();
        packet.playerName = playerName;
        locked = packet.send();
        return locked;
    }

    public void setLocked(boolean locked) {
        SetElevatorLockedPacket packet = new SetElevatorLockedPacket();
        packet.playerName = playerName;
        packet.locked = locked;
        packet.send();
        this.locked = locked;
    }

    public boolean isLockable() {
        GetElevatorLockablePacket packet = new GetElevatorLockablePacket();
        packet.playerName = playerName;
        lockable = packet.send();
        return lockable;
    }

    public void setLockable(boolean lockable) {
        SetElevatorLockablePacket packet = new SetElevatorLockablePacket();
        packet.playerName = playerName;
        packet.lockable = lockable;
        this.lockable = lockable;
    }

    public float getMovingSpeed() {
        GetElevatorMovingSpeedPacket packet = new GetElevatorMovingSpeedPacket();
        packet.playerName = playerName;
        movingSpeed = packet.send();
        return movingSpeed;
    }

    public void setMovingSpeed(float movingSpeed) {
        SetElevatorMovingSpeedPacket packet = new SetElevatorMovingSpeedPacket();
        packet.movingSpeed = movingSpeed;
        packet.playerName = playerName;
        packet.send();
        this.movingSpeed = movingSpeed;
    }

    @SuppressWarnings("unchecked")
    public List<Vector> getPositions(){
        GetElevatorPositionsPacket packet = new GetElevatorPositionsPacket();
        return packet.send();
    }

    //这里未来解决
    public Object getComponent(){
        return null;
    }

    public void use(){
        UseElevatorPacket packet = new UseElevatorPacket();
        packet.playerName = playerName;
        packet.send();
    }

}
