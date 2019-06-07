/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.event.server;

import cn.jsmod2.annotations.UseForServerInit;
import cn.jsmod2.api.server.ROUND_END_STATUS;
import cn.jsmod2.api.server.Round;
import cn.jsmod2.Smod2Server;


public class RoundEndEvent extends ServerEvent {

    private Round round;

    private ROUND_END_STATUS status;


    public RoundEndEvent(Round round, ROUND_END_STATUS status, Smod2Server smod2Server){
        super(smod2Server);
    }

    public Round getRound() {
        return round;
    }

    /** java-bean */
    @UseForServerInit
    public void setRound(Round round) {
        this.round = round;
    }

    public ROUND_END_STATUS getStatus() {
        return status;
    }

    public RoundEndEvent(){

    }
    /** java-bean */
    @UseForServerInit
    public void setStatus(ROUND_END_STATUS status) {
        this.status = status;
    }
}
