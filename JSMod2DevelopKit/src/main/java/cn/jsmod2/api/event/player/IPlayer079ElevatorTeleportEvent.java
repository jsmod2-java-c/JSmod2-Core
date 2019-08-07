package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.IElevator;
import cn.jsmod2.core.math.Vector;

public interface IPlayer079ElevatorTeleportEvent extends IPlayerEvent{

    Vector getCamera();

    IElevator getElevator();

    boolean isAllow();

    void setAllow(boolean allow);

    float getApDrain();

    void setApDrain(float apDrain);


}
