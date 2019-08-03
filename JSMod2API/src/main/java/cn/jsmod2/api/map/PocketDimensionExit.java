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

import java.io.Serializable;

public class PocketDimensionExit extends ApiId implements IPocketDimensionExit, Serializable,Cloneable {

    private PocketDimensionExitType exitType;

    private Vector position;

    public PocketDimensionExitType getExitType() {
        return exitType;
    }

    public void setExitType(PocketDimensionExitType exitType) {
        this.exitType = exitType;
    }

    public Vector getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "PocketDimensionExit{" +
                "exitType=" + exitType +
                ", position=" + position +
                '}';
    }
}
