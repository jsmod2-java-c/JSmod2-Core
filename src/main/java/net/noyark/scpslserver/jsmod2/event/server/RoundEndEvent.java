package net.noyark.scpslserver.jsmod2.event.server;

import net.noyark.Smod2Server;
import net.noyark.scpslserver.jsmod2.utils.server.ROUND_END_STATUS;
import net.noyark.scpslserver.jsmod2.utils.server.Round;


public class RoundEndEvent extends ServerEvent {

    private Round round;

    private ROUND_END_STATUS status;


    public RoundEndEvent(Round round, ROUND_END_STATUS status, Smod2Server smod2Server){
        super(smod2Server);
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public ROUND_END_STATUS getStatus() {
        return status;
    }

    public RoundEndEvent(){

    }


    public void setStatus(ROUND_END_STATUS status) {
        this.status = status;
    }
}
