package cn.jsmod2.api.item;

import cn.jsmod2.core.math.Vector;

public interface IItem {

    boolean isInWorld();

    ItemType getItemType();

    void remove();

    void drop();

    Object getComponent();

    Vector getPosition();

    void setPosition(Vector position);

    boolean isKinematic();

    void setKinematic(boolean kinematic);
}
