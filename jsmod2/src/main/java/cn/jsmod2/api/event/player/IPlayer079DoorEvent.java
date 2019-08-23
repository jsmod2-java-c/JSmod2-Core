package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.IDoor;

public interface IPlayer079DoorEvent extends IPlayerEvent{

    IDoor getDoor();

    boolean isAllow();

    void setAllow(boolean allow);

    float getApDrain();

    void setApDrain(float apDrain);

}
