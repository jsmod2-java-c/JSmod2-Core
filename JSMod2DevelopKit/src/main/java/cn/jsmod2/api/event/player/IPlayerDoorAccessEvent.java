package cn.jsmod2.api.event.player;

import cn.jsmod2.api.map.IDoor;

public interface IPlayerDoorAccessEvent extends IPlayerEvent {

    boolean isAllow();

    void setAllow(boolean allow);

    boolean isDestroy();

    void setDestroy(boolean destroy);

    IDoor getDoor();
}
