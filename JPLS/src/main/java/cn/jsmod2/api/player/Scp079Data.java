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
import cn.jsmod2.api.map.*;
import cn.jsmod2.core.ApiId;
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.DoGetStream;
import cn.jsmod2.network.DoStream;
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;
import cn.jsmod2.network.protocol.event.newstream.GetTypes;

import java.io.Serializable;
import java.util.List;

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

    private IRoom speaker;

    private Vector camera;

    public float getExp() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        exp = stream.read(playerName,"Exp",Float.class);
        return exp;
    }

    public void setExp(float exp) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"Exp",exp);
        this.exp = exp;
    }

    public int getExpToLevelUp() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        expToLevelUp = stream.read(playerName,"ExpToLevelUp",Integer.class);
        return expToLevelUp;
    }

    public void setExpToLevelUp(int expToLevelUp) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"ExpToLevelUp",expToLevelUp);
        this.expToLevelUp = expToLevelUp;
    }

    public int getLevel() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        level = stream.read(playerName,"Level",Integer.class);
        return level;
    }

    public void setLevel(int level) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"Level",level);
        this.level = level;
    }

    public float getAP() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        AP = stream.read(playerName,"AP",Float.class);
        return AP;
    }

    public void setAP(float AP) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"AP",AP);
        this.AP = AP;
    }

    public float getAPPerSecond() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        APPerSecond = stream.read(playerName,"APPerSecond",Float.class);
        return APPerSecond;
    }

    public void setAPPerSecond(float APPerSecond) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"APPerSecond",APPerSecond);
        this.APPerSecond = APPerSecond;
    }

    public float getMaxAP() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        maxAP = stream.read(playerName,"MaxAP",Float.class);
        return maxAP;
    }

    public void setMaxAP(float maxAP) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"MaxAP",maxAP);
        this.maxAP = maxAP;
    }

    public float getSpeakerAPPerSecond() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        speakerAPPerSecond = stream.read(playerName,"SpeakerAPPerSecond",Float.class);
        return speakerAPPerSecond;
    }

    public void setSpeakerAPPerSecond(float speakerAPPerSecond) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"SpeakerAPPerSecond",speakerAPPerSecond);
        this.speakerAPPerSecond = speakerAPPerSecond;
    }

    public float getYaw() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        yaw = stream.read(playerName,"Yaw",Float.class);
        return yaw;
    }


    public float getPitch() {
        SimpleGetStream stream = new SimpleGetStream(Float.class);
        pitch = stream.read(playerName,"Pitch",Float.class);
        return pitch;
    }


    public IRoom getSpeaker() {
        return speaker;
    }

    public Vector getCamera() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        camera = stream.read(playerName,"Camera",Vector.class);
        return camera;
    }

    @Override
    public void setSpeaker(IRoom iRoom) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"Speaker",speaker);
        this.speaker = iRoom;
    }

    @Override
    public IDoor[] getLockedDoors() {
        DoGetStream stream = new DoGetStream(Door.class);
        stream.playerName = playerName;
        stream.method = "GetLockedDoors";
        stream.getType = GetTypes.GET_PROTOCOL_ARRAY_WITHOUT_LIST_IN;
        return (IDoor[]) ((List)stream.send()).toArray();
    }

    @Override
    public void lock(IDoor iDoor) {
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Lock";
        stream.args = new String[]{((ApiId)iDoor).getApiId()};
        stream.send();
    }

    @Override
    public void unlock(IDoor iDoor) {
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Unlock";
        stream.args = new String[]{((ApiId)iDoor).getApiId()};
        stream.send();
    }

    @Override
    public void triggerTesla(ITeslaGate iTeslaGate) {
        DoStream stream = new DoStream();
        stream.method = "TriggerTesla";
        stream.playerName = playerName;
        stream.args = new String[]{((ApiId)iTeslaGate).getApiId()};
        stream.send();
    }

    @Override
    public void lockdown(IRoom iRoom) {
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Lockdown";
        stream.args = new String[]{((ApiId)iRoom).getApiId()};
        stream.send();
    }

    @Override
    public void setCamera(Vector vector, boolean b) {
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Camera";
        stream.args = new String[]{vector.toString(),b+""};
        stream.send();
    }

    @Override
    public void showGainExp(ExperienceType experienceType) {
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "ShowGainExp";
        stream.args = new String[]{"'"+experienceType+"'"};
        stream.send();
    }

    @Override
    public void showLevelUp(int i) {
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "ShowLevelUp";
        stream.args = new String[]{i+""};
        stream.send();
    }

    @Override
    public Object getComponent() {
        return null;
    }
}
