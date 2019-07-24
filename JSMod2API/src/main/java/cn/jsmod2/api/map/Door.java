/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.map;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.annotations.UseForServerInit;
import cn.jsmod2.core.math.Vector;

public class Door extends ApiId {

    private boolean open;

    private boolean destoryed;

    private boolean dontOpenOnWarhead;

    private boolean blockAfterWarheadDetonation;

    private boolean locked;

    private Vector position;

    private String name;

    private String permission;

    private Object getComponent(){
        return null;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isDestoryed() {
        return destoryed;
    }

    public void setDestoryed(boolean destoryed) {
        this.destoryed = destoryed;
    }

    public boolean isDontOpenOnWarhead() {
        return dontOpenOnWarhead;
    }

    public void setDontOpenOnWarhead(boolean dontOpenOnWarhead) {
        this.dontOpenOnWarhead = dontOpenOnWarhead;
    }

    public boolean isBlockAfterWarheadDetonation() {
        return blockAfterWarheadDetonation;
    }

    public void setBlockAfterWarheadDetonation(boolean blockAfterWarheadDetonation) {
        this.blockAfterWarheadDetonation = blockAfterWarheadDetonation;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Vector getPosition() {
        return position;
    }


    public String getName() {
        return name;
    }



    public String getPermission() {
        return permission;
    }

}
