/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.player;

import cn.jsmod2.api.Component;
import cn.jsmod2.api.map.IDoor;
import cn.jsmod2.api.map.IRoom;
import cn.jsmod2.api.map.ITeslaGate;
import cn.jsmod2.api.map.Room;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;

import java.io.Serializable;

public class Scp079Data extends ApiId implements IScp079Data, Serializable,Cloneable, Component {

    private float exp;

    private int expToLevelUp;

    private int level;

    private float AP;

    private float APPerSecond;

    private float maxAP;

    private float speakerAPPerSecond;

    private float yaw;

    private float pitch;

    private Room speaker;

    private Vector camera;

    public float getExp() {
        return exp;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public int getExpToLevelUp() {
        return expToLevelUp;
    }

    public void setExpToLevelUp(int expToLevelUp) {
        this.expToLevelUp = expToLevelUp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getAP() {
        return AP;
    }

    public void setAP(float AP) {
        this.AP = AP;
    }

    public float getAPPerSecond() {
        return APPerSecond;
    }

    public void setAPPerSecond(float APPerSecond) {
        this.APPerSecond = APPerSecond;
    }

    public float getMaxAP() {
        return maxAP;
    }

    public void setMaxAP(float maxAP) {
        this.maxAP = maxAP;
    }

    public float getSpeakerAPPerSecond() {
        return speakerAPPerSecond;
    }

    public void setSpeakerAPPerSecond(float speakerAPPerSecond) {
        this.speakerAPPerSecond = speakerAPPerSecond;
    }

    public float getYaw() {
        return yaw;
    }


    public float getPitch() {
        return pitch;
    }


    public Room getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Room speaker) {
        this.speaker = speaker;
    }

    public Vector getCamera() {
        return camera;
    }

    @Override
    public void setSpeaker(IRoom iRoom) {

    }

    @Override
    public IDoor[] getLockedDoors() {
        return new IDoor[0];
    }

    @Override
    public void lock(IDoor iDoor) {

    }

    @Override
    public void unlock(IDoor iDoor) {

    }

    @Override
    public void triggerTesla(ITeslaGate iTeslaGate) {

    }

    @Override
    public void lockdown(IRoom iRoom) {

    }

    @Override
    public void setCamera(Vector vector, boolean b) {

    }

    @Override
    public void showGainExp(ExperienceType experienceType) {

    }

    @Override
    public void showLevelUp(int i) {

    }

    @Override
    public Object getComponent() {
        return null;
    }
}
