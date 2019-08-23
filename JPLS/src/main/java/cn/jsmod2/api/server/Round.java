/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.server;

import cn.jsmod2.core.ApiId;
import cn.jsmod2.network.DoStream;
import cn.jsmod2.network.SimpleGetStream;
import cn.jsmod2.network.SimpleSetStream;

import java.io.Serializable;

public class Round extends ApiId implements IRound, Serializable,Cloneable {

    private IRoundStats stats;

    private int duration;

    public void endRound(){
        DoStream stream = new DoStream();
        stream.method = "EndRound";
        stream.playerName = playerName;
        stream.send();
    }

    public void addNTFUnit(String unit){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "AddNTFUnit";
        stream.args = new String[]{unit};
        stream.send();
    }

    public void MTFRespawn(boolean isCI){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "MTFRespawn";
        stream.args = new String[]{isCI+""};
        stream.send();
    }

    public void restartRound(){
        DoStream stream = new DoStream();
        stream.playerName = playerName;
        stream.method = "RestartRound";
        stream.send();
    }

    public IRoundStats getStats() {
        return stats;
    }

    public void setStats(IRoundStats stats) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"stats",stats);
        this.stats = stats;
    }

    public int getDuration() {
        SimpleGetStream stream = new SimpleGetStream(Integer.class);
        duration = stream.read(playerName,"Duration",Integer.class);
        return duration;
    }

    public void setDuration(int duration) {
        SimpleSetStream stream = new SimpleSetStream();
        stream.write(playerName,"Duration",duration);
        this.duration = duration;
    }
}
