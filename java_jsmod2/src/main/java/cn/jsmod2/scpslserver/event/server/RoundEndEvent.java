package cn.jsmod2.scpslserver.event.server;

import cn.jsmod2.scpslserver.annotations.UseForServerInit;
import cn.jsmod2.scpslserver.utils.server.ROUND_END_STATUS;
import cn.jsmod2.scpslserver.utils.server.Round;
import cn.jsmod2.scpslserver.Smod2Server;


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
