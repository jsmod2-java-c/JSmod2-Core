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
import cn.jsmod2.core.math.Vector;
import cn.jsmod2.network.DoStream;
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;

import java.io.Serializable;

public class TeslaGate extends ApiId implements Component,ITeslaGate,Cloneable, Serializable {

    private Vector triggerDistance;

    private Vector position;


    public void activate(){
        //instant=false;
        activate(false);
    }

    public void activate(boolean instant){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "Activate";
        stream.args = new String[]{instant+""};
    }

    public Vector getTriggerDistance() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        triggerDistance = stream.read(playerName,"TriggerDistance",Vector.class);
        return triggerDistance;
    }

    public void setTriggerDistance(Vector triggerDistance) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"TriggerDistance",triggerDistance);
        this.triggerDistance = triggerDistance;
    }

    public Vector getPosition() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        position = stream.read(playerName,"Position",Vector.class);
        return position;
    }

    public Object getComponent(){
        return null;
    }

}
