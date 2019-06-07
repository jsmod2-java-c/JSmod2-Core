/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.item;

import cn.jsmod2.math.Vector;

import java.io.Serializable;

public class Item implements Cloneable, Serializable {

    private boolean inWord;

    private ItemType itemType;

    private Object component;

    private Vector position;

    private boolean kinematic;

    public boolean isInWord() {
        return inWord;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void remove(){

    }
    public void drop(){

    }

    public Object getComponent() {
        return component;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public boolean isKinematic() {
        return kinematic;
    }

    public void setKinematic(boolean kinematic) {
        this.kinematic = kinematic;
    }
}
