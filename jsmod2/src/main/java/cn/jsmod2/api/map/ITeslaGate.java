package cn.jsmod2.api.map;

import cn.jsmod2.core.math.Vector;

public interface ITeslaGate {

    void activate();

    void activate(boolean instant);

    Object getComponent();

    Vector getTriggerDistance();

    void setTriggerDistance(Vector triggerDistance);

    Vector getPosition();
}
