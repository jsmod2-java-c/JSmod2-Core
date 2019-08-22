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
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;

import java.io.Serializable;

public class PocketDimensionExit extends ApiId implements IPocketDimensionExit, Serializable,Cloneable {

    private PocketDimensionExitType exitType;

    private Vector position;

    public PocketDimensionExitType getExitType() {
        SimpleGetStream stream = new SimpleGetStream(PocketDimensionExitType.class);
        exitType = stream.read(playerName,"ExitType",PocketDimensionExitType.class);
        return exitType;
    }

    public void setExitType(PocketDimensionExitType exitType) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"ExitType",exitType);
        this.exitType = exitType;
    }

    public Vector getPosition() {
        SimpleGetStream stream = new SimpleGetStream(Vector.class);
        position = stream.read(playerName,"Position",Vector.class);
        return position;
    }


}
