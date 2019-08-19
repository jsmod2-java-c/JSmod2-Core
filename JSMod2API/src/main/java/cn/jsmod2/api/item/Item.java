/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.item;


import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.protocol.item.*;

import java.io.Serializable;

public class Item extends ApiId implements Cloneable, Serializable,IItem {

    //在字段注入使用
    //playerName换成ItemId

    private boolean inWorld;

    private ItemType itemType;

    public boolean isInWorld() {
        GetItemInWorldPacket packet = new GetItemInWorldPacket();
        packet.playerName = playerName;
        inWorld = packet.send();
        return inWorld;
    }

    public ItemType getItemType() {
        GetItemTypePacket packet = new GetItemTypePacket();
        packet.playerName = playerName;
        itemType = packet.send();
        return itemType;
    }

    public void remove(){
        RemoveItemPacket packet = new RemoveItemPacket();
        packet.playerName = playerName;
        packet.send();
    }
    public void drop(){
        DropItemPacket packet = new DropItemPacket();
        packet.playerName = playerName;
        packet.send();
    }

    //这里未来解决
    public Object getComponent() {
        GetComponentPacket packet = new GetComponentPacket();
        packet.playerName = playerName;
        return packet.send();
    }

    public Vector getPosition() {
        GetPositionPacket positionPacket = new GetPositionPacket();
        positionPacket.playerName = playerName;
        return positionPacket.send();
    }

    public void setPosition(Vector position) {
        SetItemPositionPacket packet = new SetItemPositionPacket();
        packet.playerName = playerName;
        packet.position = position;
        packet.send();
    }

    public boolean isKinematic() {
        GetItemKinematicPacket kinematicPacket = new GetItemKinematicPacket();
        kinematicPacket.playerName = playerName;
        return kinematicPacket.send();
    }

    public void setKinematic(boolean kinematic) {
        SetItemKinematicPacket packet = new SetItemKinematicPacket();
        packet.playerName = playerName;
        packet.kinematic = kinematic;
        packet.send();
    }
    /** 这个方法起不了作用 */
    @Deprecated
    public void setInWorld(boolean inWord) {
        this.inWorld = inWord;
        SetItemInWorldPacket packet = new SetItemInWorldPacket();
        packet.playerName = playerName;
        packet.setInWorld = inWord;
        packet.send();
    }

}
