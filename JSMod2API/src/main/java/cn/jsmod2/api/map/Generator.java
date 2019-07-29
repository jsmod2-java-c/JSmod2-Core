/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.api.Component;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.math.Vector;

/**
 * @author magiclu550
 */

public class Generator extends ApiId implements Component {

    private boolean open;
    private boolean locked;
    private boolean hasTablet;
    private boolean engaged;
    private float startTime;
    private float timeLeft;
    private Vector position;
    private Room room = new Room();

    public void unlock(){
        if(locked){
            locked = false;
        }
    }

    public Object getComponent(){
          return null;
    }


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isHasTablet() {
        return hasTablet;
    }

    public void setHasTablet(boolean hasTablet) {
        this.hasTablet = hasTablet;
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

    public void setTimeLeft(float timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Vector getPosition() {
        return position;
    }


    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Generator{" +
                "open=" + open +
                ", locked=" + locked +
                ", hasTablet=" + hasTablet +
                ", engaged=" + engaged +
                ", startTime=" + startTime +
                ", timeLeft=" + timeLeft +
                ", position=" + position +
                ", room=" + room +
                '}';
    }
}
