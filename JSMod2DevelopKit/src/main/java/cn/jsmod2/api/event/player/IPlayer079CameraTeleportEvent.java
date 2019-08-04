package cn.jsmod2.api.event.player;

import cn.jsmod2.api.event.player.IPlayerEvent;
import cn.jsmod2.core.math.Vector;

public interface IPlayer079CameraTeleportEvent extends IPlayerEvent {

    Vector getCamera();


    boolean isAllow();

    void setAllow(boolean allow);

    float getApDrain();

    void setApDrain(float apDrain);


}
