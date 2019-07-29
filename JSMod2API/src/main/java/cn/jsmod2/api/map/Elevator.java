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

import java.util.List;

public class Elevator extends ApiId implements Component {

    private ElevatorType elevatorType;

    private ElevatorStatus elevatorStatus;

    private boolean locked;

    private boolean lockable;

    private float movingSpeed;


    public ElevatorType getElevatorType() {
        return elevatorType;
    }


    public ElevatorStatus getElevatorStatus() {
        return elevatorStatus;
    }


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLockable() {
        return lockable;
    }

    public void setLockable(boolean lockable) {
        this.lockable = lockable;
    }

    public float getMovingSpeed() {
        return movingSpeed;
    }

    public void setMovingSpeed(float movingSpeed) {
        this.movingSpeed = movingSpeed;
    }

    public List<Vector> getPositions(){
        return null;
    }

    public Object getComponent(){
        return null;
    }

    public void use(){

    }

    @Override
    public String toString() {
        return "Elevator{" +
                "elevatorType=" + elevatorType +
                ", elevatorStatus=" + elevatorStatus +
                ", locked=" + locked +
                ", lockable=" + lockable +
                ", movingSpeed=" + movingSpeed +
                '}';
    }
}
