package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.IElevator;

public interface IPlayer079ElevatorEvent extends IPlayerEvent {

    IElevator getElevator();

    boolean isAllow();

    void setAllow(boolean allow);

    float getApDrain();

    void setApDrain(float apDrain);



}
