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
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.protocol.map.door.*;

import java.io.Serializable;

public class Door extends ApiId implements IDoor, Serializable,Cloneable {

    private boolean open;

    private boolean destoryed;

    private boolean dontOpenOnWarhead;

    private boolean blockAfterWarheadDetonation;

    private boolean locked;

    private Vector position;

    private String name;

    private String permission;

    //这里未来解决
    private Object getComponent(){
        return null;
    }


    public boolean isOpen() {
        GetDoorOpenPacket packet = new GetDoorOpenPacket();
        packet.playerName = playerName;
        this.open = packet.send();
        return open;
    }

    public void setOpen(boolean open) {
        SetDoorOpenPacket packet = new SetDoorOpenPacket();
        packet.playerName = playerName;
        packet.isOpen = open;
        packet.send();
        this.open = open;
    }

    public boolean isDestoryed() {
        GetDoorDestoryedPacket packet = new GetDoorDestoryedPacket();
        packet.playerName = playerName;
        this.destoryed = packet.send();
        return destoryed;
    }

    public void setDestoryed(boolean destoryed) {
        SetDoorDestoryPacket packet = new SetDoorDestoryPacket();
        packet.playerName = playerName;
        packet.destory = destoryed;
        packet.send();
        this.destoryed = destoryed;
    }

    public boolean isDontOpenOnWarhead() {
        GetDoorDontOpenOnWarheadPacket packet = new GetDoorDontOpenOnWarheadPacket();
        packet.playerName = playerName;
        this.dontOpenOnWarhead = packet.send();
        return dontOpenOnWarhead;
    }

    public void setDontOpenOnWarhead(boolean dontOpenOnWarhead) {
        SetDoorDontOpenOnWarheadPacket packet = new SetDoorDontOpenOnWarheadPacket();
        packet.playerName = playerName;
        packet.dontOpenOnWarhead = dontOpenOnWarhead;
        packet.send();
        this.dontOpenOnWarhead = dontOpenOnWarhead;
    }

    public boolean isBlockAfterWarheadDetonation() {
        GetDoorBlockAfterWarheadDetonationPacket packet = new GetDoorBlockAfterWarheadDetonationPacket();
        packet.playerName = playerName;
        blockAfterWarheadDetonation = (Boolean) packet.send();
        return blockAfterWarheadDetonation;
    }

    public void setBlockAfterWarheadDetonation(boolean blockAfterWarheadDetonation) {
       SetDoorBlockAfterWarheadDetonationPacket packet = new SetDoorBlockAfterWarheadDetonationPacket();
       packet.playerName = playerName;
       packet.blockAfterWarheadDetonation = blockAfterWarheadDetonation;
       packet.send();
       this.blockAfterWarheadDetonation = blockAfterWarheadDetonation;
    }

    public boolean isLocked() {
        GetDoorLockedPacket packet = new GetDoorLockedPacket();
        packet.playerName = playerName;
        locked = packet.send();
        return locked;
    }

    public void setLocked(boolean locked) {
        SetDoorLockedPacket packet = new SetDoorLockedPacket();
        packet.playerName = playerName;
        packet.isLocked = locked;
        packet.send();
        this.locked = locked;
    }

    public Vector getPosition() {
        GetDoorPositionPacket packet = new GetDoorPositionPacket();
        packet.playerName = playerName;
        position = packet.send();
        return position;
    }


    public String getName() {
        GetDoorNamePacket packet = new GetDoorNamePacket();
        packet.playerName = playerName;
        name = packet.send();
        return name;
    }



    public String getPermission() {
        GetDoorPermissionPacket packet = new GetDoorPermissionPacket();
        packet.playerName = playerName;
        permission = packet.send();
        return permission;
    }


}
