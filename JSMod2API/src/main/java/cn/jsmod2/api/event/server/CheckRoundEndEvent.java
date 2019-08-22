package cn.jsmod2.api.event.server;

import cn.jsmod2.api.server.ROUND_END_STATUS;
import cn.jsmod2.api.server.Round;
import cn.jsmod2.network.PacketSender;

public class CheckRoundEndEvent extends ServerEvent implements ICheckRoundEndEvent{

    private Round round;

    private ROUND_END_STATUS status;

    @Override
    public Round getRound() {
        round = PacketSender.sendEventGetPacket(playerName,"Round",Round.class);
        return round;
    }

    public void setRound(Round round) {
        PacketSender.sendEventSetPacket(playerName,"Round",round);
        this.round = round;
    }

    @Override
    public ROUND_END_STATUS getStatus() {
        status = PacketSender.sendEventGetPacket(playerName,"Status",ROUND_END_STATUS.class);
        return status;
    }

    @Override
    public void setStatus(ROUND_END_STATUS status) {
        PacketSender.sendEventSetPacket(playerName,"Status",status);
        this.status = status;
    }
}
