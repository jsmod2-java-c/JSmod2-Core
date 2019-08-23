package cn.jsmod2.api.map;

import cn.jsmod2.core.math.Vector;

public interface IGenerator {

    void unlock();

    boolean isOpen();

    void setOpen(boolean open);

    boolean isLocked();

    boolean isHasTablet();

    void setHasTablet(boolean hasTablet);

    boolean isEngaged();


    float getStartTime();


    float getTimeLeft();

    void setTimeLeft(float timeLeft);

    Vector getPosition();

    IRoom getRoom();
}