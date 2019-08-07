package cn.jsmod2.api.map;

import cn.jsmod2.core.math.Vector;

public interface IPocketDimensionExit {

    PocketDimensionExitType getExitType();

    void setExitType(PocketDimensionExitType exitType);

    Vector getPosition();
}
