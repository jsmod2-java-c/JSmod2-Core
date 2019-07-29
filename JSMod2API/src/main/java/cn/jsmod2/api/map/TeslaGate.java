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

public class TeslaGate extends ApiId implements Component {

    private Vector triggerDistance;

    private Vector position;


    public void activate(){
        //instant=false;
        activate(false);
    }

    public void activate(boolean instant){

    }

    public Object getComponent(){
        return null;
    }

    @Override
    public String toString() {
        return "TeslaGate{" +
                "triggerDistance=" + triggerDistance +
                ", position=" + position +
                '}';
    }
}
