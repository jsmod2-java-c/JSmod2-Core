package cn.jsmod2.api.map;

import cn.jsmod2.core.math.Vector;

public interface IDoor {

    boolean isOpen();

    void setOpen(boolean open);

    boolean isDestoryed();

    void setDestoryed(boolean destoryed);

    boolean isDontOpenOnWarhead();

    void setDontOpenOnWarhead(boolean dontOpenOnWarhead);

    boolean isBlockAfterWarheadDetonation();

    void setBlockAfterWarheadDetonation(boolean blockAfterWarheadDetonation);

    boolean isLocked();

    void setLocked(boolean locked);

    Vector getPosition();


    String getName();

    String getPermission();

}
