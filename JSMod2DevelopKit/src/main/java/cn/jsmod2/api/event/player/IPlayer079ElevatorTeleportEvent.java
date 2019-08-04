package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.IElevator;
import cn.jsmod2.core.math.Vector;

public interface IPlayer079ElevatorTeleportEvent {

    Vector getCamera();

    IElevator getElevator();

    boolean isAllow();

    void setAllow(boolean allow);
}
