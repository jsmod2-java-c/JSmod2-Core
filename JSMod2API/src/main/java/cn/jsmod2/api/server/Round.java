/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.api.server;

import cn.jsmod2.core.ApiId;

import java.io.Serializable;

public class Round extends ApiId implements IRound, Serializable,Cloneable {

    private IRoundStats stats;

    private int duration;

    public void endRound(){

    }

    public void addNTFUnit(String unit){

    }

    public void MTFRespawn(boolean isCI){

    }

    public void restartRound(){

    }

    public IRoundStats getStats() {
        return stats;
    }

    public void setStats(IRoundStats stats) {
        this.stats = stats;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
