package cn.jsmod2.api.map;

import cn.jsmod2.core.math.Vector;

import java.util.List;

public interface IElevator{
    boolean isLocked();

    void setLocked(boolean locked);

    boolean isLockable();

    void setLockable(boolean lockable);

    float getMovingSpeed();

    void setMovingSpeed(float movingSpeed);

    List<Vector> getPositions();

    void use();
}
