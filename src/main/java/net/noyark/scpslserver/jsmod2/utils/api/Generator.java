package net.noyark.scpslserver.jsmod2.utils.api;

import net.noyark.scpslserver.jsmod2.math.SCPVector;

public class Generator {

    private boolean open;
    private boolean locked;
    private boolean hasTablet;
    private boolean engaged;
    private float startTime;
    private float timeLeft;
    private SCPVector position;
    private Room room;

    //TODO 解锁发包

    public void unlock(){
        if(locked){
            locked = false;
        }
    }

    //TODO 截包返回值

    public Object getComponent(){
          return null;
    }


}
