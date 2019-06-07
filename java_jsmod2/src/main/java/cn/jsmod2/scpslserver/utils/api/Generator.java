/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.scpslserver.utils.api;

import cn.jsmod2.scpslserver.math.Vector;

/**
 * @author magiclu550
 */

public class Generator {

    private boolean open;
    private boolean locked;
    private boolean hasTablet;
    private boolean engaged;
    private float startTime;
    private float timeLeft;
    private Vector position;
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

    public boolean isOpen() {
        return open;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isHasTablet() {
        return hasTablet;
    }

    public boolean isEngaged() {
        return engaged;
    }

    public float getStartTime() {
        return startTime;
    }

    public float getTimeLeft() {
        return timeLeft;
    }

    public Vector getPosition() {
        return position;
    }

    public Room getRoom() {
        return room;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setHasTablet(boolean hasTablet) {
        this.hasTablet = hasTablet;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }
}
